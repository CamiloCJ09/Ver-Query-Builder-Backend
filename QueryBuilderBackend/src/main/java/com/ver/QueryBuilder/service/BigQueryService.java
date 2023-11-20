package com.ver.QueryBuilder.service;

import ch.qos.logback.classic.Logger;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.bigquery.*;
import com.ver.QueryBuilder.dto.request.InternationalEducationInDTO;
import com.ver.QueryBuilder.model.bigqueryEntities.Country;
import com.ver.QueryBuilder.model.bigqueryEntities.Indicator;
import com.ver.QueryBuilder.model.bigqueryEntities.InternationalEducation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@Slf4j
public class BigQueryService {

    GoogleCredentials credentials;
    {
        try {
            credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/credentials.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BigQueryService() throws IOException {
    }

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

    private String queryBuilder(InternationalEducationInDTO internationalEducationInDTO){
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
    public List<InternationalEducation> getInternationalEducation(InternationalEducationInDTO internationalEducationInDTO) throws InterruptedException, IOException {

        System.out.println(internationalEducationInDTO);
        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(
                        queryBuilder(internationalEducationInDTO)
                        )
                        .setUseLegacySql(false)
                        .build();

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
