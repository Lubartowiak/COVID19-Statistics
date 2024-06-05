package com.example.covidserver.repository;

import com.example.covidserver.domain.BirthStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BirthStatRepository extends JpaRepository<BirthStat, Integer> {
}
