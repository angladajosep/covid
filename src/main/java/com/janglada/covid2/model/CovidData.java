package com.janglada.covid2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString(exclude="dateEntity")
public class CovidData {

    @Id
    @GeneratedValue
    private UUID id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "date_id")
    DateEntity dateEntity;

    @ManyToOne
    @JoinColumn(name = "state_id")
    StateEntity stateEntity;

    private int caseCumulative;
    private int deathCumulative;
    private int uciCumulative;
    private int hospitalCumulative;
    private int recoveredCumulative;


/*    @Override
    public String toString() {
        return "CovidData{" +
                "id=" + id +
                ", casos=" + casos +
                ", hospitalizados=" + hospitalizados +
                ", uCI=" + uCI +
                ", fallecidos=" + fallecidos +
                ", recuperados=" + recuperados +
                '}';
    }*/
}