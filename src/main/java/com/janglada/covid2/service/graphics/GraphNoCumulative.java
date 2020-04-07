package com.janglada.covid2.service.graphics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphNoCumulative extends Graph {

    private Map<String,Integer> statePreviusValue = null;

    public GraphNoCumulative(final boolean population) {
        super(Boolean.FALSE, population);
        this.statePreviusValue  = new HashMap<>();
    }

    @Override
    public List<Double> calculate(List<Double> covidValues, int covidValue, String stateId) {
        Integer covidPreviusValue = statePreviusValue.get(stateId);
        if (null == covidPreviusValue) {
            covidPreviusValue = Integer.valueOf(0);
        }
        covidValues.add(calculatevalue(covidValue - covidPreviusValue.intValue(), stateId, this.isPopulation()));
        statePreviusValue.put(stateId, covidValue);

        return covidValues;
    }
}
