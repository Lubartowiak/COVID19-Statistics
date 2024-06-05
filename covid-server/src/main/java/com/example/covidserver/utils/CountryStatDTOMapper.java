package com.example.covidserver.utils;

import com.example.covidserver.DTO.CountryStatDTO;
import com.example.covidserver.domain.CountryStat;

public class CountryStatDTOMapper {

    public static CountryStatDTO map(CountryStat countryStat) {
        CountryStatDTO statDTO = new CountryStatDTO();
        statDTO.setStat_id(countryStat.getStat_id());
        statDTO.setCountry_population(countryStat.getCountry_population());
        statDTO.setTotal_deaths(countryStat.getTotal_deaths());
        statDTO.setTotal_cases(countryStat.getTotal_cases());
        statDTO.setTotal_recovered(countryStat.getTotal_recovered());
        statDTO.setActive_cases(countryStat.getActive_cases());
        statDTO.setCountry(countryStat.getCountry());
        return statDTO;
    }
}
