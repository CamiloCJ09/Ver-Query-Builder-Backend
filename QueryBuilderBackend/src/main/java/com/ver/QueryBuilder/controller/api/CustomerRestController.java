package com.ver.QueryBuilder.controller.api;

import com.ver.QueryBuilder.dto.request.CustomerInDTO;
import com.ver.QueryBuilder.dto.response.CustomerOutDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(CustomerRestController.BASE_URL)
public interface CustomerRestController {

        String BASE_URL = "/api/costumer";

        @GetMapping("/")
        @CrossOrigin(origins = "*")
        public List<CustomerOutDTO> getAllCostumers();

        @PostMapping("/")
        @CrossOrigin(origins = "*")
        public CustomerOutDTO createCostumer(@Valid @RequestBody CustomerInDTO customerInDTO);

}
