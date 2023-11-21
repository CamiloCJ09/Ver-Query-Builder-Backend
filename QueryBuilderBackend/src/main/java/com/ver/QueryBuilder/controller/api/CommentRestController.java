package com.ver.QueryBuilder.controller.api;

import com.ver.QueryBuilder.dto.request.CommentInDTO;
import com.ver.QueryBuilder.dto.response.CommentOutDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(CommentRestController.BASE_URL)
public interface CommentRestController {

    String BASE_URL = "/api/comment";

    @GetMapping("/{queryId}")
    @CrossOrigin(origins = "*")
    public List<CommentOutDTO> getCommentByQueryId(@PathVariable String queryId);

    @PostMapping("/")
    @CrossOrigin(origins = "*")
    public CommentOutDTO createComment(@Valid @RequestBody CommentInDTO commentInDTO);
}
