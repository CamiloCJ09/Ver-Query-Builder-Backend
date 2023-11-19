package com.ver.QueryBuilder.service;

import com.ver.QueryBuilder.mapper.CommentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;

}
