package com.ver.QueryBuilder.controller.api;

import com.ver.QueryBuilder.dto.request.CommentInDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RequestMapping(CommentRestController.BASE_URL)
public interface CommentRestController {

    String BASE_URL = "/api/comment";

    @GetMapping("/{queryId}")
    public String getCommentByQueryId(@PathVariable String queryId);

    @PostMapping("/{queryId}")
    public String createComment(@PathVariable String queryId, @Valid @RequestBody CommentInDTO commentInDTO);
}
