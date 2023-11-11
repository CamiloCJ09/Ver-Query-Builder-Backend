package com.ver.QueryBuilder.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(value="/bigquery")
public interface BigQueryRestController {

    @GetMapping("/")
    String test() throws InterruptedException;
}
