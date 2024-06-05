package com.example.covidserver.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "countrystat")
public class CountryStat {

    @Id
    @Column(name = "stat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stat_id;
    @Column(name = "country_population")
    private Long country_population;
    @Column(name = "total_cases")
    private Integer total_cases;
    @Column(name = "total_deaths")
    private Integer total_deaths;
    @Column(name = "total_recovered")
    private Integer total_recovered;
    @Column(name = "active_cases")
    private Long active_cases;
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = true)
    @JsonBackReference("country-reference")
    private Country country;
}
