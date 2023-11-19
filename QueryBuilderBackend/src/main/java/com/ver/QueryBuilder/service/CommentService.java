package com.ver.QueryBuilder.service;

import com.ver.QueryBuilder.dto.request.CommentInDTO;
import com.ver.QueryBuilder.dto.response.CommentOutDTO;
import com.ver.QueryBuilder.mapper.CommentMapper;
import com.ver.QueryBuilder.model.ownEntites.Comment;
import com.ver.QueryBuilder.repository.CommentRepository;
import com.ver.QueryBuilder.repository.CostumerRepository;
import com.ver.QueryBuilder.repository.UserQueryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;
    private final CostumerRepository costumerRepository;
    private final UserQueryRepository userQueryRepository;
    private final CommentRepository commentRepository;

    public List<CommentOutDTO> getCommentByQueryId(String queryId) {
        return commentRepository.findAllById(UUID.fromString(queryId)).stream().filter(Optional::isPresent)
                .map(Optional::get).toList();
    }

    public CommentOutDTO createComment(CommentInDTO commentInDTO) {
        Comment comment = commentMapper.toComment(commentInDTO);
        comment.setCostumer(costumerRepository.findById(UUID.fromString(commentInDTO.getCostumerId()))
                .orElseThrow(() -> new RuntimeException("Costumer not found")));
        comment.setUserQuery(userQueryRepository.findById(UUID.fromString(commentInDTO.getUserQueryId()))
                .orElseThrow(() -> new RuntimeException("UserQuery not found")));
        return commentMapper.toCommentOutDTO(commentRepository.save(comment));
    }

}
