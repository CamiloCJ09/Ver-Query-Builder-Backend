package com.ver.QueryBuilder.mapper;

import com.ver.QueryBuilder.dto.request.CustomerInDTO;
import com.ver.QueryBuilder.dto.response.CustomerOutDTO;
import com.ver.QueryBuilder.model.ownEntites.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    final UserQueryMapper userQueryMapper = new UserQueryMapperImpl();
    final CommentMapper commentMapper = new CommentMapperImpl();
    Customer toCostumer(CustomerInDTO customerInDTO);

    @Mapping(target = "userQueries", expression = "java(customer.getUserQueries().stream().map(userQueryMapper::toUserQueryOutDTO).toList())")
    @Mapping(target = "comments", expression = "java(customer.getComments().stream().map(commentMapper::toCommentOutDTO).toList())")
    CustomerOutDTO toCostumerOutDTO(Customer customer);
}
