package com.ver.QueryBuilder.mapper;

import com.ver.QueryBuilder.dto.request.UserQueryInDTO;
import com.ver.QueryBuilder.dto.response.UserQueryOutDTO;
import com.ver.QueryBuilder.model.ownEntites.UserQuery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserQueryMapper {

    final CommentMapper commentMapper = new CommentMapperImpl();
    @Mapping(target = "customer", ignore = true)
    UserQuery toUserQuery(UserQueryInDTO userQueryInDTO);

    @Mapping(target = "costumer", expression = "java(userQuery.getCustomer().getUsername())")
    @Mapping(target = "comments", expression = "java(userQuery.getComments().stream().map(commentMapper::toCommentOutDTO).toList())")
    UserQueryOutDTO toUserQueryOutDTO(UserQuery userQuery);
}
