package com.ver.QueryBuilder.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryOutDTO {

    private String queryname;

    private String query;

    private String comment;

    private String costumer;

    private List<CommentOutDTO> comments;
}
