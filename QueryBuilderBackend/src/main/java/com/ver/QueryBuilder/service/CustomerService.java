package com.ver.QueryBuilder.service;

import com.ver.QueryBuilder.dto.request.CustomerInDTO;
import com.ver.QueryBuilder.dto.response.CustomerOutDTO;
import com.ver.QueryBuilder.mapper.CustomerMapper;
import com.ver.QueryBuilder.model.ownEntites.Customer;
import com.ver.QueryBuilder.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerOutDTO createCostumer(CustomerInDTO customerInDTO) {
        Customer customer = customerMapper.toCostumer(customerInDTO);
        customer.setComments(new ArrayList<>());
        customer.setUserQueries(new ArrayList<>());
        customerRepository.save(customer);
        return customerMapper.toCostumerOutDTO(customer);
    }

    public List<CustomerOutDTO> getAllCostumers() {
        return customerRepository.findAll().stream().map(customerMapper::toCostumerOutDTO).toList();
    }
}
