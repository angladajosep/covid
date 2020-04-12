package com.janglada.covid2.model;

public enum StateEnum {

    AN("AN", "ANDALUCIA", 8426405, "rgba(0,0,0, 1.0)"),
    AR("AR", "ARAGON", 1320794, "rgba(0,0,128, 1.0)"),
    AS("AS", "ASTURIAS", 1022293, "rgba(0,0,255, 1.0)"),
    IB("IB", "BALEARES", 1187808, "rgba(0,128,0, 1.0)"),
    CN("CN", "CANARIAS", 2207225, "rgba(0,128,128, 1.0)"),
    CB("CB", "CANTABRIA", 581684, "rgba(0,255,0, 1.0)"),
    CM("CM", "CASTILLA_MANCHA", 2035505, "rgba(0,255,255, 1.0)"),
    CL("CL", "CASTILLA_LEON", 2408083, "rgba(128,0,0, 1.0)"),
    CT("CT", "CATALUNYA", 7565099, "rgba(128,0,128, 1.0)"),
    CE("CE", "CEUTA", 84843, "rgba(128,128,0, 1.0)"),
    VC("VC", "VALENCIA", 4974475, "rgba(128,128,128, 1.0)"),
    EX("EX", "EXTREMADURA", 1065371, "rgba(192,192,192, 1.0)"),
    GA("GA", "GALICA", 2700330, "rgba(255,0,0, 1.0)"),
    MD("MD", "MADRID", 6640705, "rgba(255,0,255, 1.0)"),
    ML("ML", "MELILLA", 84714, "rgba(255,255,0, 1.0)"),
    MC("MC", "MURCIA", 1487698, "rgba(0,0,0, 0.5)"),
    NC("NC", "NAVARRA", 653846, "rgba(0,0,128, 0.5)"),
    PV("PV", "PAIS_VASCO", 2178048, "rgba(0,0,255, 0.5)"),
    RI("RI", "RIOJA", 313582, "rgba(0,128,0, 0.5)");

    private String id;
    private String stateName;
    private Integer population;
    private String rgba;

    StateEnum(String id, String stateName, Integer population, String rgba) {
        this.id = id;
        this.population = population;
        this.stateName = stateName;
        this.rgba = rgba;
    }


    public String getId() {
        return this.id;
    }

    public String getStateName() {
        return this.stateName;
    }

    public String getRgba() {
        return this.rgba;
    }

    public Integer getPopulation() {
        return this.population;
    }

    @Override
    public String toString() {
        return this.stateName;
    }
}