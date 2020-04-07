package com.janglada.covid2.model.api;

import com.janglada.covid2.model.StateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class DataSet implements Serializable {

    private List<Double> data;
    private String label;
    private String borderColor;

    public DataSet(final List<Double> data, final StateEnum stateEnum) {
        this.data = data;
        this.label = stateEnum.getStateName();
        this.borderColor = stateEnum.getRgba();
    }
}
