package com.example.covidserver.repository;

import com.example.covidserver.domain.Continent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, Integer> {

    @Query("SELECT c FROM Continent c WHERE c.continent_name = ?1")
    Optional<Continent> findContinentByName(String continent);
}
