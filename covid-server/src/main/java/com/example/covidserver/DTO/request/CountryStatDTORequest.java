package com.example.covidserver.DTO.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryStatDTORequest implements Serializable {

    @JsonProperty("stat_id")
    private Integer stat_id;
    @JsonProperty("country_population")
    private Long country_population;
    @JsonProperty("total_cases")
    private Integer total_cases;
    @JsonProperty("total_deaths")
    private Integer total_deaths;
    @JsonProperty("total_recovered")
    private Integer total_recovered;
    @JsonProperty("active_cases")
    private Long active_cases;
}