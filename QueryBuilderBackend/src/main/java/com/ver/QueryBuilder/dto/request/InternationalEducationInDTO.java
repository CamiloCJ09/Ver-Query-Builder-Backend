package com.ver.QueryBuilder.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InternationalEducationInDTO {

    private String countryName;
    private String countryCode;
    private String seriesCode;
    private int year;
    private String value;

}
