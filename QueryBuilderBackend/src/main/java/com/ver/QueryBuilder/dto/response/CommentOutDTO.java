package com.ver.QueryBuilder.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentOutDTO {

    private String comment;

    private UUID costumerId;

    private UUID userQueryId;
}
