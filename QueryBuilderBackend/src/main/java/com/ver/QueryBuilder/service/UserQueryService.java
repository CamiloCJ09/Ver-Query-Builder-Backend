package com.ver.QueryBuilder.service;

import com.ver.QueryBuilder.dto.request.CustomerInDTO;
import com.ver.QueryBuilder.dto.request.UserQueryInDTO;
import com.ver.QueryBuilder.dto.response.UserQueryOutDTO;
import com.ver.QueryBuilder.mapper.CustomerMapper;
import com.ver.QueryBuilder.mapper.UserQueryMapper;
import com.ver.QueryBuilder.model.ownEntites.Customer;
import com.ver.QueryBuilder.model.ownEntites.UserQuery;
import com.ver.QueryBuilder.repository.CustomerRepository;
import com.ver.QueryBuilder.repository.UserQueryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing user query-related operations.
 *
 * This class provides methods to retrieve information about existing user queries and create new user queries.
 * It interacts with the UserQueryRepository, CustomerRepository, CustomerService, and mappers for converting
 * between DTOs and entities.
 *
 * @author Camilo Campaz
 * @version 1.0
 * @since 2023-11-21
 */
@Service
@AllArgsConstructor
public class UserQueryService {

    private final UserQueryRepository userQueryRepository;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final UserQueryMapper userQueryMapper;


    /**
     * Retrieves a list of all user queries.
     *
     * @return A List of UserQueryOutDTOs representing all existing user queries.
     *
     * @see UserQueryRepository#findAll()
     * @see List#stream()
     * @see UserQueryMapper#toUserQueryOutDTO(UserQuery)
     *
     */
    public List<UserQueryOutDTO> getAllUserQueries() {
        return userQueryRepository.findAll().stream().map(userQueryMapper::toUserQueryOutDTO).toList();
    }

    /**
     * Creates a new user query based on the provided UserQueryInDTO.
     *
     * @param userQueryInDTO The UserQueryInDTO containing information for creating the user query.
     * @return A UserQueryOutDTO representing the created user query.
     * @throws RuntimeException Thrown if the associated customer is not found.
     *
     * @see UserQueryMapper#toUserQuery(UserQueryInDTO)
     * @see CustomerRepository#findCostumerByUsername(String)
     * @see CustomerService#createCostumer(CustomerInDTO)
     * @see CustomerRepository#findCostumerByUsername(String)
     * @see UserQueryMapper#toUserQueryOutDTO(UserQuery)
     * @see UserQueryRepository#save(Object)
     *
     */
    public UserQueryOutDTO createUserQuery(UserQueryInDTO userQueryInDTO) {
        System.out.println(userQueryInDTO);
        UserQuery userQuery = userQueryMapper.toUserQuery(userQueryInDTO);
        Customer customer = customerRepository.findCostumerByUsername(userQueryInDTO.getCostumer()).orElse(null);
        if (customer == null) {
            customer = customerMapper.toCustomer(customerService.createCostumer(new CustomerInDTO(userQueryInDTO.getCostumer())));
        }
        userQuery.setCustomer(customerRepository.findCostumerByUsername(userQueryInDTO.getCostumer())
                .orElseThrow(() -> new RuntimeException("Customer not found")));
        userQuery.setComments(new ArrayList<>());
        return userQueryMapper.toUserQueryOutDTO(userQueryRepository.save(userQuery));
    }
}
