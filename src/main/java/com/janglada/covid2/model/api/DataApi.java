package com.janglada.covid2.model.api;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DataApi implements Serializable {

    List<String> labels;
    List<DataSet> datasets;
}
