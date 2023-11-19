package com.ver.QueryBuilder.controller.implementation;

import com.ver.QueryBuilder.controller.api.CostumerRestController;
import com.ver.QueryBuilder.dto.request.CostumerInDTO;
import com.ver.QueryBuilder.dto.response.CostumerOutDTO;
import com.ver.QueryBuilder.service.CostumerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CostumerRestControllerImp implements CostumerRestController {

    private final CostumerService costumerService;

    public List<CostumerOutDTO> getAllCostumers() {
        return costumerService.getAllCostumers();
    }

    @Override
    public CostumerOutDTO createCostumer(CostumerInDTO costumerInDTO) {
        return costumerService.createCostumer(costumerInDTO);
    }


}
