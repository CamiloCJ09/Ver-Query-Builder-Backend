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
public class UserQueryInDTO {

    @NotBlank
    private String queryname;

    @NotBlank
    private String query;

    @NotBlank
    private String comment;

    @NotBlank
    private String costumer;

    private String countryCode;

    private String seriesCode;

    private int year;

    private String value;

}
