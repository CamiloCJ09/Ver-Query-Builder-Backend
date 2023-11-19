package com.ver.QueryBuilder.model.bigqueryEntities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeriesSummary {

    String series_code;
    String topic;
    String indicator_name;
    String short_definition;
    String long_definition;
    String unit_of_measure;
    String periodicity;
    String base_period;
    String other_notes;
    String aggregation_method;
    String limitations_and_exceptions;
    String notes_from_original_source;
    String general_comments;
    String source;
    String statistical_concept_and_methodology;
    String development_relevance;
    String related_source_links;
    String other_web_links;
    String related_indicators;
    String license_type;
}
