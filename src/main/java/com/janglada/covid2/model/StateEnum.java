package com.janglada.covid2.model;

public enum StateEnum {

    AN("AN", "ANDALUCIA", "rgba(0,0,0, 1.0)"),
    AR("AR", "ARAGON", "rgba(0,0,128, 1.0)"),
    AS("AS", "ASTURIAS", "rgba(0,0,255, 1.0)"),
    IB("IB", "BALEARES", "rgba(0,128,0, 1.0)"),
    CN("CN", "CANARIAS", "rgba(0,128,128, 1.0)"),
    CB("CB", "CANTABRIA", "rgba(0,255,0, 1.0)"),
    CM("CM", "CASTILLA_MANCHA", "rgba(0,255,255, 1.0)"),
    CL("CL", "CASTILLA_LEON", "rgba(128,0,0, 1.0)"),
    CT("CT", "CATALUNYA", "rgba(128,0,128, 1.0)"),
    CE("CE", "CEUTA", "rgba(128,128,0, 1.0)"),
    VC("VC", "VALENCIA", "rgba(128,128,128, 1.0)"),
    EX("EX", "EXTREMADURA", "rgba(192,192,192, 1.0)"),
    GA("GA", "GALICA", "rgba(255,0,0, 1.0)"),
    MD("MD", "MADRID", "rgba(255,0,255, 1.0)"),
    ME("ME", "MELILLA", "rgba(255,255,0, 1.0)"),
    MC("MC", "MURCIA", "rgba(0,0,0, 0.5)"),
    NC("NC", "NAVARRA", "rgba(0,0,128, 0.5)"),
    PV("PV", "PAIS_VASCO", "rgba(0,0,255, 0.5)"),
    RI("RI", "RIOJA", "rgba(0,128,0, 0.5)");


    private String id;
    private String stateName;
    private String rgba;

    StateEnum(String id, String stateName, String rgba) {
        this.id = id;
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

    @Override
    public String toString() {
        return this.stateName;
    }
}