package com.ver.QueryBuilder.controller.implementation;

import com.ver.QueryBuilder.controller.api.BigQueryRestController;
import com.ver.QueryBuilder.service.BigQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class BigQueryRestControllerImp implements BigQueryRestController {

    private final BigQueryService bigQueryService;

    public String test() throws InterruptedException {
        return bigQueryService.basicQuery();
    }
}
