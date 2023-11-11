package com.ver.QueryBuilder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountrySummary {
    String country_code;
    String short_name;
    String table_name;
    String long_name;
    String two_alpha_code;
    String currency_unit;
    String special_notes;
    String region;
    String income_group;
    String wb_two_code;
    String national_accounts_base_year;
    String national_accounts_reference_year;
    String sna_price_valuation;
    String lending_category;
    String other_groups;
    String system_of_national_accounts;
    String alternative_conversion_factor;
    String ppp_survey_year;
    String balance_of_payments_manual_in_use;
    String external_debt_reporting_status;
    String system_of_trade;
    String government_accounting_concept;
    String imf_data_dissemination_standard;
    String latest_population_census;
    String latest_household_survey;
    String source_of_most_recent_income_and_expenditure_data;
    String vital_registration_complete;
    String latest_agricultural_census;
    Integer latest_industrial_data;
    Integer latest_trade_data;
    String latest_water_withdrawal_data;
}
