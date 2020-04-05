package com.janglada.covid2.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
public class DateEntity {

    @Id
    @GeneratedValue
    private UUID dateId;

    @Column(unique = true)
    private LocalDate date;

    @OneToMany(mappedBy = "dateEntity")
    Set<CovidData> covidData;

}
