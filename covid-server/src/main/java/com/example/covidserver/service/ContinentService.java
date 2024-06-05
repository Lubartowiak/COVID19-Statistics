package com.example.covidserver.service;

import com.example.covidserver.DTO.ContinentDTO;
import com.example.covidserver.domain.Continent;
import com.example.covidserver.repository.ContinentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContinentService {

    private ContinentRepository continentRepository;

    public List<ContinentDTO> allContinents() {
        return continentRepository.findAll().stream().map(continent -> new ContinentDTO(
                continent.getContinent_id(),
                continent.getContinent_name()
        )).collect(Collectors.toList());
    }
}
