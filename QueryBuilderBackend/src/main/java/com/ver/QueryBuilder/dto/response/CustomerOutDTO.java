package com.ver.QueryBuilder.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOutDTO {

    private UUID id;

    private String username;

    private List<UserQueryOutDTO> userQueries;

    private List<CommentOutDTO> comments;
}
