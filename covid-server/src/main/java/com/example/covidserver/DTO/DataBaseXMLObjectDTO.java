package com.example.covidserver.DTO;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "covid_data")
public class DataBaseXMLObjectDTO {
    private String country_name;
    private String continent_name;
    private Long country_population;
    private Integer total_cases;
    private Integer total_deaths;
    private Integer total_recovered;
    private Long active_cases;
    private List<BirthStatDTO> birth_stats;
}
