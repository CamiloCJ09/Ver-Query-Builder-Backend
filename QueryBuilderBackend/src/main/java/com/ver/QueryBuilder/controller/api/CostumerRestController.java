package com.ver.QueryBuilder.controller.api;

import com.ver.QueryBuilder.dto.request.CostumerInDTO;
import com.ver.QueryBuilder.dto.response.CostumerOutDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(CostumerRestController.BASE_URL)
public interface CostumerRestController {

        String BASE_URL = "/api/costumer";

        @GetMapping("/")
        public List<CostumerOutDTO> getAllCostumers();

        @PostMapping("/")
        public CostumerOutDTO createCostumer(@Valid @RequestBody CostumerInDTO costumerInDTO);

}
