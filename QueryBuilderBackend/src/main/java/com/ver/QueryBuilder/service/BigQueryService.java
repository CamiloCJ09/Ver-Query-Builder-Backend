package com.ver.QueryBuilder.service;

import ch.qos.logback.classic.Logger;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.bigquery.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;




@Service
@Slf4j
public class BigQueryService {

    GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/credentials.json"));

    public BigQueryService() throws IOException {
    }

    public String basicQuery() throws InterruptedException {
        log.info("basicQuery Started");


        BigQuery bigquery = BigQueryOptions.newBuilder()
                .setCredentials(credentials)
                .build().getService();

        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(
                                "SELECT * FROM `bigquery-public-data.world_bank_intl_education.country_series_definitions` LIMIT 10")
                        .setUseLegacySql(false)
                        .build();

        String jobIdStr = UUID.randomUUID().toString();

        log.info("service gctj jobIdStr: " + jobIdStr);

        // Create a job ID so that we can safely retry.
        JobId jobId = JobId.of(jobIdStr);

        Job queryJob = bigquery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());

        log.info("service gctj queryJob: " + queryJob);

        try {
            queryJob = queryJob.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("service gctj queryJob: " + queryJob);
        
        // Check for errors
        if (queryJob == null) {
            throw new RuntimeException("Job no longer exists");
        } else if (queryJob.getStatus().getError() != null) {
            // You can also look at queryJob.getStatus().getExecutionErrors() for all
            // errors, not just the latest one.
            throw new RuntimeException(queryJob.getStatus().getError().toString());
        }

        // Get the results.

        QueryResponse response = bigquery.getQueryResults(jobId);

        log.info("service gctj response: " + response);

        TableResult result = queryJob.getQueryResults();

        log.info("service gctj result: " + result);


        return result.toString();
    }
}
