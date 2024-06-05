package com.example.covidserver.repository;

import com.example.covidserver.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query("SELECT c FROM Country c WHERE c.country_name = ?1")
    Optional<Country> findCountryByName(String country);
}
