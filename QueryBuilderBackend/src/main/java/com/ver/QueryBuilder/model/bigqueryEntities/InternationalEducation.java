package com.ver.QueryBuilder.model.bigqueryEntities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternationalEducation {

    String country_name;
    String country_code;
    String indicator_name;
    String indicator_code;
    Float value;
    Integer year;
}
