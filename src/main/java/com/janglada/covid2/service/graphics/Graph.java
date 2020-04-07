package com.janglada.covid2.service.graphics;

import lombok.Getter;

import java.util.List;

import static com.janglada.covid2.model.StateEnum.valueOf;

@Getter
public abstract class Graph {

    private final boolean cumulative;
    private final boolean population;

    protected Graph(boolean cumulative, boolean population) {
        this.cumulative = cumulative;
        this.population = population;
    }

    public abstract List<Double> calculate(List<Double> covidValues,final int covidValue, final String stateId) ;



    protected double calculatevalue(int covidValue, String stateId, boolean population) {
        double result = 0d;

        if (population) {
            if (covidValue != 0) {
                result = (double) (covidValue * (100)) / valueOf(stateId).getPopulation();
            }
        } else {
            result = covidValue;
        }

        return result;
    }


}
