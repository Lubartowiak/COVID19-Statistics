package com.example.covidserver.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "continent")
public class Continent {

    @Id
    @Column(name = "continent_id")
    private Integer continent_id;
    @Column(name = "continent_name")
    private String continent_name;
}
