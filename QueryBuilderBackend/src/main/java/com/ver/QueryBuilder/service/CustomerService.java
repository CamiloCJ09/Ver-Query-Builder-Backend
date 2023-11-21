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


/**
 * Service class for managing customer-related operations.
 *
 * This class provides methods to create customers and retrieve information about existing customers.
 * It interacts with the CustomerRepository for database operations and uses a CustomerMapper
 * for converting between DTOs and entities.
 *
 * @author Camilo Campaz
 * @version 1.0
 * @since 2023-11-21
 */
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    /**
     * Creates a new customer based on the provided CustomerInDTO.
     *
     * @param customerInDTO The CustomerInDTO containing information for creating the customer.
     * @return A CustomerOutDTO representing the created customer.
     *
     * @see CustomerMapper#toCostumer(CustomerInDTO)
     * @see Customer#setComments(List)
     * @see Customer#setUserQueries(List)
     * @see CustomerRepository#save(Object)
     * @see CustomerMapper#toCostumerOutDTO(Customer)
     *
     */
    public CustomerOutDTO createCostumer(CustomerInDTO customerInDTO) {
        Customer customer = customerMapper.toCostumer(customerInDTO);
        customer.setComments(new ArrayList<>());
        customer.setUserQueries(new ArrayList<>());
        customerRepository.save(customer);
        return customerMapper.toCostumerOutDTO(customer);
    }

    /**
     * Retrieves a list of all customers.
     *
     * @return A List of CustomerOutDTOs representing all existing customers.
     *
     * @see CustomerRepository#findAll()
     * @see List#stream()
     * @see CustomerMapper#toCostumerOutDTO(Customer)
     *
     */
    public List<CustomerOutDTO> getAllCostumers() {
        return customerRepository.findAll().stream().map(customerMapper::toCostumerOutDTO).toList();
    }
}
