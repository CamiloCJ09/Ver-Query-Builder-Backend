package com.ver.QueryBuilder.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(BigQueryRestController.BASE_URL)
public interface BigQueryRestController {

    String BASE_URL = "/api/bigquery";

    @GetMapping("/")
    String test() throws InterruptedException;
}
