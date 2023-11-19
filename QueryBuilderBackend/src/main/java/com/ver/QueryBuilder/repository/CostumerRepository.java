package com.ver.QueryBuilder.repository;

import com.ver.QueryBuilder.model.ownEntites.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CostumerRepository extends JpaRepository<Costumer, UUID> {

    Optional<Costumer> findCostumerByUsername(String username);
}
