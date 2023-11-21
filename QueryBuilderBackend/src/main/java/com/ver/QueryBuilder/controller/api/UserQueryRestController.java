package com.ver.QueryBuilder.controller.api;

import com.ver.QueryBuilder.dto.request.UserQueryInDTO;
import com.ver.QueryBuilder.dto.response.UserQueryOutDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(UserQueryRestController.BASE_URL)
public interface UserQueryRestController {

    String BASE_URL = "/api/userQuery";

    @GetMapping("/")
    @CrossOrigin(origins = "*")
    public List<UserQueryOutDTO> getAllUserQueries();

    @PostMapping("/")
    @CrossOrigin(origins = "*")
    public UserQueryOutDTO createUserQuery(@Valid @RequestBody UserQueryInDTO userQueryInDTO);
}
