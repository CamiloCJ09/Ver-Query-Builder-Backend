package com.ver.QueryBuilder.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentInDTO {

    @NotBlank
    private String comment;

    @NotBlank
    private String costumerId;

    @NotBlank
    private String userQueryId;
}
