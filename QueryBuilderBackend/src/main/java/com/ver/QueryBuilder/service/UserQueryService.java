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

@Service
@AllArgsConstructor
public class UserQueryService {

    private final UserQueryRepository userQueryRepository;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final UserQueryMapper userQueryMapper;

    public List<UserQueryOutDTO> getAllUserQueries() {
        return userQueryRepository.findAll().stream().map(userQueryMapper::toUserQueryOutDTO).toList();
    }

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
