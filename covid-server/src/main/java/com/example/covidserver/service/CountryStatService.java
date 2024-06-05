package com.example.covidserver.service;

import com.example.covidserver.DTO.CountryStatDTO;
import com.example.covidserver.DTO.DataBaseObjectDTO;
import com.example.covidserver.DTO.DataBaseXMLObjectDTO;
import com.example.covidserver.DTO.ImportDatabaseResponse;
import com.example.covidserver.domain.BirthStat;
import com.example.covidserver.domain.Continent;
import com.example.covidserver.domain.Country;
import com.example.covidserver.domain.CountryStat;
import com.example.covidserver.repository.BirthStatRepository;
import com.example.covidserver.repository.ContinentRepository;
import com.example.covidserver.repository.CountryRepository;
import com.example.covidserver.repository.CountryStatRepository;
import com.example.covidserver.utils.CountryStatDTOMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CountryStatService {

    private final CountryStatRepository statRepository;
    private final CountryRepository countryRepository;
    private final ContinentRepository continentRepository;
    private final BirthStatRepository birthStatRepository;
    private final Logger logger = LoggerFactory.getLogger(CountryStatService.class);

    public List<CountryStatDTO> allStats() {
        return statRepository.findAll()
                .stream()
                .map(CountryStatDTOMapper::map)
                .collect(Collectors.toList());
    }

    public ResponseEntity<ImportDatabaseResponse> importJSONDatabase(List<DataBaseObjectDTO> database) {
            for (DataBaseObjectDTO object : database){
                try {
                    Country founded = countryRepository.findCountryByName(object.getCountry_name()).orElse(null);
                    if (Objects.isNull(founded)) {
                        Continent continent = continentRepository.findContinentByName(object.getContinent_name()).orElse(null);
                        if (Objects.nonNull(continent)) {
                            Country newCountry = new Country();
                            newCountry.setCountry_name(object.getCountry_name());
                            newCountry.setContinent(continent);
                            newCountry = countryRepository.save(newCountry);

                            Country finalNewCountry = newCountry;
                            List<BirthStat> birthStats = object.getBirth_stats().stream().map(birthStatDTO -> {
                                BirthStat birthStat = new BirthStat();
                                birthStat.setYear(birthStatDTO.getYear());
                                birthStat.setBirths(birthStatDTO.getBirths());
                                birthStat.setCountry(finalNewCountry);
                                return birthStat;
                            }).toList();
                            birthStatRepository.saveAll(birthStats);

                            CountryStat newStatistics = new CountryStat();
                            newStatistics.setCountry(newCountry);
                            newStatistics.setCountry_population(object.getCountry_population());
                            newStatistics.setTotal_cases(object.getTotal_cases());
                            newStatistics.setTotal_recovered(object.getTotal_recovered());
                            newStatistics.setTotal_deaths(object.getTotal_deaths());
                            newStatistics.setActive_cases(object.getActive_cases());
                            statRepository.save(newStatistics);
                        } else {
                            logger.warn("The object is not proper due to non existing Continent");
                            throw new Exception("JSON is in bad format");
                        }
                    } else {
                        logger.info("Country already exists. Object was no inserted to database");
                        throw new Exception("One of countries already exists");
                    }
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ImportDatabaseResponse(e.getMessage()));
                }
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ImportDatabaseResponse("Data imported successfully"));
    }

    public ResponseEntity<ImportDatabaseResponse> importXMLDatabase(List<DataBaseXMLObjectDTO> database) {
        for (DataBaseXMLObjectDTO object : database){
            try {
                Country founded = countryRepository.findCountryByName(object.getCountry_name()).orElse(null);
                if (Objects.isNull(founded)) {
                    Continent continent = continentRepository.findContinentByName(object.getContinent_name()).orElse(null);
                    if (Objects.nonNull(continent)) {
                        Country newCountry = new Country();
                        newCountry.setCountry_name(object.getCountry_name());
                        newCountry.setContinent(continent);
                        newCountry = countryRepository.save(newCountry);

                        Country finalNewCountry = newCountry;
                        List<BirthStat> birthStats = object.getBirth_stats().stream().map(birthStatDTO -> {
                            BirthStat birthStat = new BirthStat();
                            birthStat.setYear(birthStatDTO.getYear());
                            birthStat.setBirths(birthStatDTO.getBirths());
                            birthStat.setCountry(finalNewCountry);
                            return birthStat;
                        }).toList();
                        birthStatRepository.saveAll(birthStats);

                        CountryStat newStatistics = new CountryStat();
                        newStatistics.setCountry(newCountry);
                        newStatistics.setCountry_population(object.getCountry_population());
                        newStatistics.setTotal_cases(object.getTotal_cases());
                        newStatistics.setTotal_recovered(object.getTotal_recovered());
                        newStatistics.setTotal_deaths(object.getTotal_deaths());
                        newStatistics.setActive_cases(object.getActive_cases());
                        statRepository.save(newStatistics);
                    } else {
                        logger.warn("The object is not proper due to non existing Continent");
                        throw new Exception("JSON is in bad format");
                    }
                } else {
                    logger.info("Country already exists. Object was no inserted to database");
                    throw new Exception("One of countries already exists");
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ImportDatabaseResponse(e.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ImportDatabaseResponse("Data imported successfully"));
    }
}
