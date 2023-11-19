package com.ver.QueryBuilder.controller.implementation;

import com.ver.QueryBuilder.controller.api.CommentRestController;
import com.ver.QueryBuilder.dto.request.CommentInDTO;
import com.ver.QueryBuilder.dto.response.CommentOutDTO;
import com.ver.QueryBuilder.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CommentRestControllerImp implements CommentRestController {

    private final CommentService commentService;
    public List<CommentOutDTO> getCommentByQueryId(String queryId) {

        return commentService.getCommentByQueryId(queryId);
    }

    public CommentOutDTO createComment(CommentInDTO commentInDTO) {
        return commentService.createComment(commentInDTO);
    }
}
