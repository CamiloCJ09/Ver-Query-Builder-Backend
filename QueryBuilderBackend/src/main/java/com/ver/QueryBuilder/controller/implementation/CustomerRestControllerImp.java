package com.ver.QueryBuilder.controller.implementation;

import com.ver.QueryBuilder.controller.api.CustomerRestController;
import com.ver.QueryBuilder.dto.request.CustomerInDTO;
import com.ver.QueryBuilder.dto.response.CustomerOutDTO;
import com.ver.QueryBuilder.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerRestControllerImp implements CustomerRestController {

    private final CustomerService customerService;

    public List<CustomerOutDTO> getAllCostumers() {
        return customerService.getAllCostumers();
    }

    @Override
    public CustomerOutDTO createCostumer(CustomerInDTO customerInDTO) {
        return customerService.createCostumer(customerInDTO);
    }


}
