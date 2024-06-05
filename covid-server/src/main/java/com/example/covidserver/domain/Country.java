package com.example.covidserver.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {

    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer country_id;

    @Column(name = "country_name")
    private String country_name;

    @ManyToOne()
    @JoinColumn(name = "continent_id", nullable = true)
    private Continent continent;

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
    private List<BirthStat> birthStats;
}
