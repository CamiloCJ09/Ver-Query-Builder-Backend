package com.ver.QueryBuilder.controller.api;

import com.ver.QueryBuilder.dto.request.UserQueryInDTO;
import com.ver.QueryBuilder.dto.response.UserQueryOutDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(UserQueryRestController.BASE_URL)
public interface UserQueryRestController {

    String BASE_URL = "/api/userQuery";

    @GetMapping("/")
    public List<UserQueryOutDTO> getAllUserQueries();

    @PostMapping("/")
    public UserQueryOutDTO createUserQuery(@Valid @RequestBody UserQueryInDTO userQueryInDTO);
}
