package com.ver.QueryBuilder.model.bigqueryEntities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountrySeriesDefinitions {

    String country;

    String series;
    String description;

}
