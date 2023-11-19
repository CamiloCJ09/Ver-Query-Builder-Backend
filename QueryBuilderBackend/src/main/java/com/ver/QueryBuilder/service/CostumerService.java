package com.ver.QueryBuilder.service;

import com.ver.QueryBuilder.dto.request.CostumerInDTO;
import com.ver.QueryBuilder.dto.response.CostumerOutDTO;
import com.ver.QueryBuilder.mapper.CostumerMapper;
import com.ver.QueryBuilder.model.ownEntites.Costumer;
import com.ver.QueryBuilder.repository.CostumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CostumerService {

    private final CostumerMapper costumerMapper;
    private final CostumerRepository costumerRepository;

    public CostumerOutDTO createCostumer(CostumerInDTO costumerInDTO) {
        Costumer costumer = costumerMapper.toCostumer(costumerInDTO);
        costumerRepository.save(costumer);
        return costumerMapper.toCostumerOutDTO(costumer);
    }

    public List<CostumerOutDTO> getAllCostumers() {
        return costumerRepository.findAll().stream().map(costumerMapper::toCostumerOutDTO).toList();
    }
}
