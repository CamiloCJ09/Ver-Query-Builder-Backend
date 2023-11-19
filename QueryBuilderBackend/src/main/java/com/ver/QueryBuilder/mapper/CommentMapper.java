package com.ver.QueryBuilder.mapper;

import com.ver.QueryBuilder.dto.request.CommentInDTO;
import com.ver.QueryBuilder.dto.response.CommentOutDTO;
import com.ver.QueryBuilder.model.ownEntites.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment toComment(CommentInDTO commentInDTO);

    @Mapping(target = "costumerId", expression = "java(comment.getCustomer().getId())")
    @Mapping(target = "userQueryId", expression = "java(comment.getUserQuery().getId())")
    CommentOutDTO toCommentOutDTO(Comment comment);
}
