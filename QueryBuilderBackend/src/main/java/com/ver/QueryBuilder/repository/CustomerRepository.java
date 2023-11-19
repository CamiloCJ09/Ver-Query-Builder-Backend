package com.ver.QueryBuilder.repository;

import com.ver.QueryBuilder.model.ownEntites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findCostumerByUsername(String username);
}
