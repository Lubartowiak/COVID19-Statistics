package com.example.covidserver.DTO;

import com.example.covidserver.domain.Continent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {

    private Integer country_id;
    private String country_name;
    private Continent continent;
}
