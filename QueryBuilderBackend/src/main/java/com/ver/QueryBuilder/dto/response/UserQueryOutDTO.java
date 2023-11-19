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
public class UserQueryOutDTO {

    private UUID id;

    private String queryname;

    private String query;

    private String comment;

    private String costumer;

    private List<CommentOutDTO> comments;
}
