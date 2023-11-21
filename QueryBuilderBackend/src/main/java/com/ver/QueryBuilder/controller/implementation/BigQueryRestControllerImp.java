package com.ver.QueryBuilder.controller.implementation;

import com.ver.QueryBuilder.controller.api.BigQueryRestController;
import com.ver.QueryBuilder.dto.request.InternationalEducationInDTO;
import com.ver.QueryBuilder.dto.response.QueryExecuteInDTO;
import com.ver.QueryBuilder.model.bigqueryEntities.Country;
import com.ver.QueryBuilder.model.bigqueryEntities.Indicator;
import com.ver.QueryBuilder.model.bigqueryEntities.InternationalEducation;
import com.ver.QueryBuilder.service.BigQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
@AllArgsConstructor
public class BigQueryRestControllerImp implements BigQueryRestController {

    private final BigQueryService bigQueryService;

    public String test() throws InterruptedException {
        return null;
    }

    @Override
    public List<Indicator> getIndicators() throws InterruptedException, IOException {
        return bigQueryService.getAllIndicators();
    }

    @Override
    public List<Country> getCountries() throws InterruptedException, IOException {
        return bigQueryService.getAllCountries();
    }

    @Override
    public List<InternationalEducation> getInternationalEducation(InternationalEducationInDTO internationalEducationInDTO) throws InterruptedException, IOException {
        return bigQueryService.getInternationalEducation(internationalEducationInDTO);
    }

    @Override
    public String getInternationalEducationQuery(InternationalEducationInDTO internationalEducationInDTO) throws InterruptedException, IOException {
        return bigQueryService.queryBuilder(internationalEducationInDTO);
    }

    @Override
    public List<InternationalEducation> getInternationalEducationQuery(QueryExecuteInDTO query) throws InterruptedException, IOException {
        return bigQueryService.getInternationalEducationQuery(query);
    }


}
