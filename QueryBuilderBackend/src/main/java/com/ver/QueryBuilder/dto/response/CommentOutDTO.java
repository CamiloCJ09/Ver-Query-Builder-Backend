package com.ver.QueryBuilder.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentOutDTO {

    private String comment;

    private String costumerId;

    private String userQueryId;
}
