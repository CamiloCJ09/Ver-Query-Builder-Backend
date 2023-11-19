package com.ver.QueryBuilder.mapper;

import com.ver.QueryBuilder.dto.request.UserQueryInDTO;
import com.ver.QueryBuilder.dto.response.UserQueryOutDTO;
import com.ver.QueryBuilder.model.ownEntites.UserQuery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserQueryMapper {

    @Mapping(target = "costumer", ignore = true)
    UserQuery toUserQuery(UserQueryInDTO userQueryInDTO);

    @Mapping(target = "costumer", expression = "java(userQuery.getCostumer().getUsername())")
    UserQueryOutDTO toUserQueryOutDTO(UserQuery userQuery);
}
