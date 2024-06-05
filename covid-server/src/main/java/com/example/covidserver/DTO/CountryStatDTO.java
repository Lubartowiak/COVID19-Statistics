package com.example.covidserver.DTO;

import com.example.covidserver.domain.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryStatDTO implements Serializable {

    private Integer stat_id;
    private Long country_population;
    private Integer total_cases;
    private Integer total_deaths;
    private Integer total_recovered;
    private Long active_cases;
    private Country country;
}
