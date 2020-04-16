package com.janglada.covid2.service.graphics;

import java.util.List;

public class GraphCumulative extends Graph {

    public GraphCumulative(final boolean population) {
        super(Boolean.TRUE, population);
    }

    @Override
    public List<Double> calculate(List<Double> covidValues, int covidValue, String stateId) {
        covidValues.add(calculatevalue(covidValue, stateId, this.isPopulation()));
        return covidValues;
    }
}
