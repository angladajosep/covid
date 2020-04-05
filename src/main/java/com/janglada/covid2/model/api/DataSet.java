package com.janglada.covid2.model.api;

import com.janglada.covid2.model.StateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class DataSet implements Serializable {

    private List<Integer> data;
    private String label;
    private String backgroundColor;

    public DataSet(final List<Integer> data, final StateEnum stateEnum) {
        this.data = data;
        this.label = stateEnum.getStateName();
        this.backgroundColor = stateEnum.getRgba();
    }
}
