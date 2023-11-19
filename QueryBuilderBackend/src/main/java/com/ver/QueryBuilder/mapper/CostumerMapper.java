package com.ver.QueryBuilder.mapper;

import com.ver.QueryBuilder.dto.request.CostumerInDTO;
import com.ver.QueryBuilder.dto.response.CostumerOutDTO;
import com.ver.QueryBuilder.model.ownEntites.Costumer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CostumerMapper {

    final UserQueryMapper userQueryMapper = new UserQueryMapperImpl();
    final CommentMapper commentMapper = new CommentMapperImpl();
    Costumer toCostumer(CostumerInDTO costumerInDTO);

    @Mapping(target = "userQueries", expression = "java(costumer.getUserQueries().stream().map(userQueryMapper::toUserQueryOutDTO).toList())")
    @Mapping(target = "comments", expression = "java(costumer.getComments().stream().map(commentMapper::toCommentOutDTO).toList())")
    CostumerOutDTO toCostumerOutDTO(Costumer costumer);
}
