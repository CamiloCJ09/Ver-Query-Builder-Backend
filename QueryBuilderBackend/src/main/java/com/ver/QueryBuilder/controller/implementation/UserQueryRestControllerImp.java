package com.ver.QueryBuilder.controller.implementation;

import com.ver.QueryBuilder.controller.api.UserQueryRestController;
import com.ver.QueryBuilder.dto.request.UserQueryInDTO;
import com.ver.QueryBuilder.dto.response.UserQueryOutDTO;
import com.ver.QueryBuilder.service.UserQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserQueryRestControllerImp implements UserQueryRestController {

    private final UserQueryService userQueryService;
    public List<UserQueryOutDTO> getAllUserQueries() {
        return userQueryService.getAllUserQueries();
    }

    public UserQueryOutDTO createUserQuery(UserQueryInDTO userQueryInDTO) {
        return userQueryService.createUserQuery(userQueryInDTO);
    }
}
