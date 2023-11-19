package com.ver.QueryBuilder.service;

import com.ver.QueryBuilder.dto.request.UserQueryInDTO;
import com.ver.QueryBuilder.dto.response.UserQueryOutDTO;
import com.ver.QueryBuilder.mapper.UserQueryMapper;
import com.ver.QueryBuilder.model.ownEntites.UserQuery;
import com.ver.QueryBuilder.repository.CostumerRepository;
import com.ver.QueryBuilder.repository.UserQueryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserQueryService {

    private final UserQueryRepository userQueryRepository;
    private final CostumerRepository costumerRepository;
    private final UserQueryMapper userQueryMapper;

    public List<UserQueryOutDTO> getAllUserQueries() {
        return userQueryRepository.findAll().stream().map(userQueryMapper::toUserQueryOutDTO).toList();
    }

    public UserQueryOutDTO createUserQuery(UserQueryInDTO userQueryInDTO) {
        UserQuery userQuery = userQueryMapper.toUserQuery(userQueryInDTO);
        userQuery.setCostumer(costumerRepository.findCostumerByUsername(userQueryInDTO.getCostumer())
                .orElseThrow(() -> new RuntimeException("Costumer not found")));
        userQuery.setComments(new ArrayList<>());
        return userQueryMapper.toUserQueryOutDTO(userQueryRepository.save(userQuery));
    }
}
