package com.ver.QueryBuilder.controller.api;

import com.ver.QueryBuilder.dto.request.InternationalEducationInDTO;
import com.ver.QueryBuilder.dto.response.QueryExecuteInDTO;
import com.ver.QueryBuilder.model.bigqueryEntities.Country;
import com.ver.QueryBuilder.model.bigqueryEntities.Indicator;
import com.ver.QueryBuilder.model.bigqueryEntities.InternationalEducation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RequestMapping(BigQueryRestController.BASE_URL)
public interface BigQueryRestController {

    String BASE_URL = "/api/bigquery";

    @GetMapping("/")
    @CrossOrigin(origins = "*")
    String test() throws InterruptedException;

    @GetMapping("/indicators")
    @CrossOrigin(origins = "*")
    List<Indicator> getIndicators() throws InterruptedException, IOException;

    @GetMapping("/countries")
    @CrossOrigin(origins = "*")
    List<Country> getCountries() throws InterruptedException, IOException;

    @PostMapping ("/internationalEducation")
    @CrossOrigin(origins = "*")
    List<InternationalEducation> getInternationalEducation(@Valid @RequestBody InternationalEducationInDTO internationalEducationInDTO) throws InterruptedException, IOException;

    @PostMapping ("/internationalEducation/get")
    @CrossOrigin(origins = "*")
    String getInternationalEducationQuery(@Valid @RequestBody InternationalEducationInDTO internationalEducationInDTO) throws InterruptedException, IOException;

    @PostMapping ("/internationalEducation/query")
    @CrossOrigin(origins = "*")
    List<InternationalEducation> getInternationalEducationQuery(@Valid @RequestBody QueryExecuteInDTO queryExecuteInDTO) throws InterruptedException, IOException;
}
