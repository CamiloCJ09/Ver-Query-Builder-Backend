package com.ver.QueryBuilder.controller.implementation;

import com.ver.QueryBuilder.controller.api.CommentRestController;
import com.ver.QueryBuilder.dto.request.CommentInDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CommentRestControllerImp implements CommentRestController {
    public String getCommentByQueryId(String queryId) {

        return null;
    }

    public String createComment(String queryId, CommentInDTO commentInDTO) {
        return null;
    }
}
