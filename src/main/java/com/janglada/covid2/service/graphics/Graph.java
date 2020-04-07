package com.janglada.covid2.service.graphics;

public abstract class Graph {

    private final boolean cumulative;
    private final boolean population;

    protected Graph(boolean cumulative, boolean population) {
        this.cumulative = cumulative;
        this.population = population;
    }

    public abstract void calculate();


}
