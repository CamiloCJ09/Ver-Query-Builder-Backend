package com.ver.QueryBuilder.unit.service;
import com.ver.QueryBuilder.dto.request.CustomerInDTO;
import com.ver.QueryBuilder.dto.response.CustomerOutDTO;
import com.ver.QueryBuilder.mapper.CustomerMapper;
import com.ver.QueryBuilder.mapper.CustomerMapperImpl;
import com.ver.QueryBuilder.model.ownEntites.*;
import com.ver.QueryBuilder.repository.CustomerRepository;
import com.ver.QueryBuilder.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class CustomerServiceTest {

    private CustomerRepository customerRepository;
    private CustomerService customerServiceUnderTest;
    private CustomerMapper customerMapper;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        customerMapper = spy(CustomerMapperImpl.class);
        customerServiceUnderTest = new CustomerService(customerMapper, customerRepository);
    }

    public static Customer createCustomer() {
        return Customer.builder()
                .username("username")
                .comments(new ArrayList<>())
                .userQueries(new ArrayList<>())
                .id(UUID.randomUUID())
                .build();
    }
    @Test
    void testCreateCostumer() {
        // Setup
        CustomerInDTO user = CustomerInDTO.builder().username("username").build();
        CustomerOutDTO customerOutDTO = CustomerOutDTO.builder()
                .username("username")
                .id(UUID.randomUUID())
                .build();

        when(customerMapper.toCustomer(any())).thenReturn(createCustomer());
        when(customerRepository.save(any())).thenReturn(createCustomer());
        when(customerMapper.toCostumerOutDTO(any())).thenReturn(customerOutDTO);
        //when(customerServiceUnderTest.createCostumer(any())).thenReturn(customerOutDTO);

        // Run the test
        customerServiceUnderTest.createCostumer(user);

        // Verify the results
        //verify(customerRepository).findCostumerByUsername(user.getUsername());
        verify(customerRepository,times(1)).save(any());
    }

    @Test
    void testGetAllCostumers() {
        // Setup
        when(customerRepository.findAll()).thenReturn(new ArrayList<>());

        // Run the test
        final var result = customerServiceUnderTest.getAllCostumers();

        // Verify the results
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void createCustomerWithoutUsername() {
        // Setup
        CustomerInDTO user = CustomerInDTO.builder().build();
        CustomerOutDTO customerOutDTO = CustomerOutDTO.builder()
                .username("username")
                .id(UUID.randomUUID())
                .build();

        when(customerMapper.toCustomer(any())).thenReturn(createCustomer());
        when(customerRepository.save(any())).thenReturn(createCustomer());
        when(customerMapper.toCostumerOutDTO(any())).thenReturn(customerOutDTO);
        //when(customerServiceUnderTest.createCostumer(any())).thenReturn(customerOutDTO);

        // Run the test
        customerServiceUnderTest.createCostumer(user);

        // Verify the results
        //verify(customerRepository).findCostumerByUsername(user.getUsername());
        assertThrows(NullPointerException.class, () -> customerServiceUnderTest.createCostumer(user));
        //verify(customerRepository,times(1)).save(any());
    }
}
