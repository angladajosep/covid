package com.janglada.covid2.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ComboApi implements Serializable {

    private String text;
    private String id;
}
