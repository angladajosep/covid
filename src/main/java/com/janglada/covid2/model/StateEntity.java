package com.janglada.covid2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString(exclude = "covidData")
public class StateEntity {

    @Id
    @GeneratedValue
    private UUID stateId;

    @Column(unique = true)
    private String stateName;

    @JsonIgnore
    @OneToMany(mappedBy = "stateEntity")
    Set<CovidData> covidData;


}
