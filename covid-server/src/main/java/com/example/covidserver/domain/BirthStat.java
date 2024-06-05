package com.example.covidserver.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "birthstat")
public class BirthStat {
    @Id
    @Column(name = "birth_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer birth_id;

    @Column(name = "year")
    private String year;

    @Column(name = "births")
    private Integer births;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = true)
    @JsonBackReference("country-reference")
    private Country country;
}
