package com.ver.QueryBuilder.controller.api;

import com.ver.QueryBuilder.dto.request.InternationalEducationInDTO;
import com.ver.QueryBuilder.model.bigqueryEntities.Country;
import com.ver.QueryBuilder.model.bigqueryEntities.Indicator;
import com.ver.QueryBuilder.model.bigqueryEntities.InternationalEducation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RequestMapping(BigQueryRestController.BASE_URL)
public interface BigQueryRestController {

    String BASE_URL = "/api/bigquery";

    @GetMapping("/")
    String test() throws InterruptedException;

    @GetMapping("/indicators")
    List<Indicator> getIndicators() throws InterruptedException, IOException;

    @GetMapping("/countries")
    List<Country> getCountries() throws InterruptedException, IOException;

    @GetMapping("/internationalEducation")
    List<InternationalEducation> getInternationalEducation(@Valid @RequestBody InternationalEducationInDTO internationalEducationInDTO) throws InterruptedException, IOException;
}
