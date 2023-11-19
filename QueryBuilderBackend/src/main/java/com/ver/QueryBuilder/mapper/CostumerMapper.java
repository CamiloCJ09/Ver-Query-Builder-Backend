package com.ver.QueryBuilder.mapper;

import com.ver.QueryBuilder.dto.request.CostumerInDTO;
import com.ver.QueryBuilder.dto.response.CostumerOutDTO;
import com.ver.QueryBuilder.model.ownEntites.Costumer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CostumerMapper {

    Costumer toCostumer(CostumerInDTO costumerInDTO);

    CostumerOutDTO toCostumerOutDTO(Costumer costumer);
}
