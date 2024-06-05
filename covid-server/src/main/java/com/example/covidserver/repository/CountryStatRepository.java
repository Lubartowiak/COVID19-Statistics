package com.example.covidserver.repository;

import com.example.covidserver.domain.CountryStat;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryStatRepository extends JpaRepository<CountryStat, Integer> {

}
