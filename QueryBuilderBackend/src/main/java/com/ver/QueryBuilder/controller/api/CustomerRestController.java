package com.ver.QueryBuilder.controller.api;

import com.ver.QueryBuilder.dto.request.CustomerInDTO;
import com.ver.QueryBuilder.dto.response.CustomerOutDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(CustomerRestController.BASE_URL)
public interface CustomerRestController {

        String BASE_URL = "/api/costumer";

        @GetMapping("/")
        public List<CustomerOutDTO> getAllCostumers();

        @PostMapping("/")
        public CustomerOutDTO createCostumer(@Valid @RequestBody CustomerInDTO customerInDTO);

}
