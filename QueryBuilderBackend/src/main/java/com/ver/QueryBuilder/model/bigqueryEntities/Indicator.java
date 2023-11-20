package com.ver.QueryBuilder.model.bigqueryEntities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Indicator {

        String series_code;

        String indicator_name;
}
