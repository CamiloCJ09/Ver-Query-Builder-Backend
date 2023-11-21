package com.ver.QueryBuilder.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.bigquery.*;
import com.ver.QueryBuilder.dto.request.InternationalEducationInDTO;
import com.ver.QueryBuilder.dto.response.QueryExecuteInDTO;
import com.ver.QueryBuilder.model.bigqueryEntities.Country;
import com.ver.QueryBuilder.model.bigqueryEntities.Indicator;
import com.ver.QueryBuilder.model.bigqueryEntities.InternationalEducation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service class for interacting with Google BigQuery to retrieve international education data.
 *
 * This class provides methods to query information related to indicators, countries, and international education.
 * It uses Google BigQuery for executing SQL-like queries and retrieving data.
 *
 * @author Camilo Campaz
 * @version 1.0
 * @since 2023-11-21
 */
@Service
@Slf4j
public class BigQueryService {

    GoogleCredentials credentials;
    /**
     * Constructs a new BigQueryService and initializes Google Cloud credentials from the specified file path.
     *
     * @param env  The Spring environment.
     * @param path The file path to the Google Cloud credentials file.
     */
    @Autowired
    public void GetDataToQueryService(Environment env, @Value("${spring.credentials.google}") String path) {
        try {
            System.out.println(env.getProperty("spring.credentials.google"));
            this.credentials = GoogleCredentials.fromStream(new FileInputStream(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public BigQueryService() throws IOException {
    }

    /**
     * Configures and executes a BigQuery job based on the provided query configuration.
     *
     * This method sets up the necessary components for executing a BigQuery job, including creating a unique job ID,
     * associating it with the provided query configuration, and waiting for the job to complete.
     * If the job fails or encounters an error, exceptions are thrown accordingly.
     *
     * @param queryConfig The BigQuery query configuration specifying the query to execute.
     * @return The completed BigQuery job containing the query results.
     * @throws IOException          Thrown if an I/O error occurs during the BigQuery job execution.
     * @throws InterruptedException Thrown if the execution is interrupted while waiting for the query job to complete.
     * @throws RuntimeException     Thrown if the connection to BigQuery fails or if the query job encounters an error.
     *
     * @see BigQueryService#credentials
     * @see BigQueryOptions.Builder
     * @see JobInfo.Builder
     * @see JobInfo.Builder#setJobId(JobId)
     * @see JobInfo.Builder#build()
     * @see Job#getStatus()
     * @see JobStatus#getError()
     * @see Job
     * @see QueryJobConfiguration
     *
     * @since 2023-11-21
     */
    private Job config(QueryJobConfiguration queryConfig) throws IOException {
        BigQuery bigquery = BigQueryOptions.newBuilder()
                .setCredentials(credentials)
                .build().getService();

        String jobIdStr = UUID.randomUUID().toString();
        JobId jobId = JobId.of(jobIdStr);
        Job queryJob = bigquery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());

        try {
            queryJob = queryJob.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (queryJob == null) {
            throw new RuntimeException("Connect Failed");
        } else if (queryJob.getStatus().getError() != null) {
            throw new RuntimeException(queryJob.getStatus().getError().toString());
        }

        return queryJob;
    }

    /**
     * Retrieves a list of all indicators from the BigQuery dataset.
     *
     * @return List of Indicator objects.
     * @throws InterruptedException Thrown if the execution is interrupted while waiting for the query to complete.
     * @throws IOException          Thrown if an I/O error occurs.
     */
    public List<Indicator> getAllIndicators() throws InterruptedException, IOException {

        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(
                                "SELECT series_code, indicator_name FROM `bigquery-public-data.world_bank_intl_education.series_summary` where topic = \"Expenditures\" and other_notes = \"Secondary\"")
                        .setUseLegacySql(false)
                        .build();

        Job queryJob = config(queryConfig);

        TableResult result = queryJob.getQueryResults();

        return StreamSupport.stream(result.iterateAll().spliterator(), false)
                .map(row -> Indicator.builder()
                        .seriesCode(row.get("series_code").getStringValue())
                        .indicatorName(row.get("indicator_name").getStringValue())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of all countries from the BigQuery dataset.
     *
     * @return List of Country objects.
     * @throws InterruptedException Thrown if the execution is interrupted while waiting for the query to complete.
     * @throws IOException          Thrown if an I/O error occurs.
     */
    public List<Country> getAllCountries() throws InterruptedException, IOException {

        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(
                                "SELECT country_code, table_name " +
                                        "FROM `bigquery-public-data.world_bank_intl_education.country_summary` " +
                                        "where region is not null")
                        .setUseLegacySql(false)
                        .build();

        Job queryJob = config(queryConfig);

        TableResult result = queryJob.getQueryResults();

        return StreamSupport.stream(result.iterateAll().spliterator(), false)
                .map(row -> Country.builder()
                        .countryCode(row.get("country_code").getStringValue())
                        .countryName(row.get("table_name").getStringValue())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * Builds a SQL-like query for retrieving international education data based on the provided input DTO.
     *
     * @param internationalEducationInDTO The input DTO containing filter criteria.
     * @return The constructed SQL-like query as a String.
     */
    public String queryBuilder(InternationalEducationInDTO internationalEducationInDTO){
        StringBuilder query = new StringBuilder();
        query.append("SELECT\n" + "  country_name, country_code, indicator_name, indicator_code, value, year\n");
        query.append("FROM  `bigquery-public-data.world_bank_intl_education.international_education`\n");
        query.append("WHERE\n");
        if (!internationalEducationInDTO.getSeriesCode().equals(""))
            query.append("  indicator_code = '").append(internationalEducationInDTO.getSeriesCode()).append("'\n");
        if (!internationalEducationInDTO.getCountryCode().equals(""))
            query.append("  AND country_code = '").append(internationalEducationInDTO.getCountryCode()).append("'\n");
        if (internationalEducationInDTO.getYear() != 0)
            query.append("  AND year > ").append(internationalEducationInDTO.getYear()).append("\n");
        if (!internationalEducationInDTO.getValue().equals(""))
            query.append("  AND value > ").append(internationalEducationInDTO.getValue()).append("\n");
        query.append("ORDER BY year");

        return query.toString();
    }

    /**
     * Retrieves a list of international education data based on the provided input DTO.
     *
     * @param internationalEducationInDTO The input DTO containing filter criteria.
     * @return List of InternationalEducation objects.
     * @throws InterruptedException Thrown if the execution is interrupted while waiting for the query to complete.
     * @throws IOException          Thrown if an I/O error occurs.
     */
    public List<InternationalEducation> getInternationalEducation(InternationalEducationInDTO internationalEducationInDTO) throws InterruptedException, IOException {

        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(
                        queryBuilder(internationalEducationInDTO)
                        )
                        .setUseLegacySql(false)
                        .build();

        return getInternationalEducations(queryConfig);

    }

    /**
     * Retrieves a list of InternationalEducation objects based on the provided query in the QueryExecuteInDTO.
     *
     * This method takes a QueryExecuteInDTO object, extracts the query string, creates a BigQuery query configuration,
     * executes the query, and processes the results into a list of InternationalEducation objects.
     *
     * @param query The QueryExecuteInDTO containing the query string to execute.
     * @return A List of InternationalEducation objects containing the retrieved data.
     * @throws InterruptedException Thrown if the execution is interrupted while waiting for the query to complete.
     * @throws IOException          Thrown if an I/O error occurs during the BigQuery job execution.
     *
     * @see QueryExecuteInDTO
     * @see QueryExecuteInDTO#getQuery()
     * @see BigQueryService#getInternationalEducations(QueryJobConfiguration)
     * @see QueryJobConfiguration
     * @see QueryJobConfiguration#newBuilder(String)
     * @see InternationalEducation
     *
     * @since 2023-11-21
     */
    public List<InternationalEducation> getInternationalEducationQuery(QueryExecuteInDTO query) throws InterruptedException, IOException {

        System.out.println(query);
        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(
                            query.getQuery()
                        )
                        .setUseLegacySql(false)
                        .build();

        return getInternationalEducations(queryConfig);

    }


    /**
     * Retrieves a list of InternationalEducation objects based on the provided BigQuery query configuration.
     *
     * This method executes a BigQuery query using the specified configuration, processes the results,
     * and maps them to a list of InternationalEducation objects.
     *
     * @param queryConfig The BigQuery query configuration specifying the query to execute.
     * @return A List of InternationalEducation objects containing the retrieved data.
     * @throws IOException          Thrown if an I/O error occurs during the BigQuery job execution.
     * @throws InterruptedException Thrown if the execution is interrupted while waiting for the query to complete.
     *
     * @see BigQueryService#config(QueryJobConfiguration)
     * @see Job
     * @see TableResult
     * @see StreamSupport
     * @see Collectors
     * @see InternationalEducation
     *
     * @since 2023-11-21
     */
    private List<InternationalEducation> getInternationalEducations(QueryJobConfiguration queryConfig) throws IOException, InterruptedException {
        Job queryJob = config(queryConfig);
        System.out.println(queryJob);

        TableResult result = queryJob.getQueryResults();
        return StreamSupport.stream(result.iterateAll().spliterator(), false)
                .map(row -> InternationalEducation.builder()
                        .country_name(row.get("country_name").getStringValue())
                        .country_code(row.get("country_code").getStringValue())
                        .indicator_name(row.get("indicator_name").getStringValue())
                        .indicator_code(row.get("indicator_code").getStringValue())
                        .value(row.get("value").getNumericValue().floatValue())
                        .year(row.get("year").getNumericValue().intValue())
                        .build())
                .collect(Collectors.toList());
    }
}
