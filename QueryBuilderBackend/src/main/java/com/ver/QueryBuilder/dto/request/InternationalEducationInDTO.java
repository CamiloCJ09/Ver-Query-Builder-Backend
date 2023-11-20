package com.ver.QueryBuilder.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InternationalEducationInDTO {

    private String countryName;
    @NotNull
    private String countryCode;
    @NotBlank
    private String seriesCode;
    private int year;
    private String value;

}
