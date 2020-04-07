package com.janglada.covid2.service;

import com.janglada.covid2.model.CovidData;
import com.janglada.covid2.model.DateEntity;
import com.janglada.covid2.model.api.DataApi;
import com.janglada.covid2.model.api.DataSet;
import com.janglada.covid2.repository.CovidDataRepository;
import com.janglada.covid2.service.graphics.Graph;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import static com.janglada.covid2.model.StateEnum.valueOf;

@Service
@Slf4j
public class CovidService {

    private final CovidDataRepository covidDataRepository;
    private final DateService dateService;

    public static final ToIntFunction<CovidData> CASE_VALUE = CovidData::getCaseCumulative;
    public static final ToIntFunction<CovidData> DEATH_VALUE = CovidData::getDeathCumulative;
    public static final ToIntFunction<CovidData> UCI_VALUE = CovidData::getUciCumulative;
    public static final ToIntFunction<CovidData> HOSPITAL_VALUE = CovidData::getHospitalCumulative;
    public static final ToIntFunction<CovidData> RECOVERED_VALUE = CovidData::getRecoveredCumulative;

    public CovidService(final CovidDataRepository covidDataRepository, final DateService dateService) {
        this.covidDataRepository = covidDataRepository;
        this.dateService = dateService;
    }

    public List<CovidData> getValues() {
        return covidDataRepository.findAll();
    }
    public List<DateEntity> getDateEntityValues() {
        return dateService.findAll();
    }


    public DataApi getApiValues(ToIntFunction<CovidData> covidDataFunction, List<String> statesId, Graph graph) {
        List<DateEntity> dateEntityList = dateService.findAll();

        DataApi dataApi = new DataApi();
        dataApi.setLabels(dateEntityList.stream().map(x -> x.getDate().toString()).collect(Collectors.toList()));

        Map<String, List<Double>> statesReport = new HashMap<>();
        for (DateEntity dateEntity : dateEntityList) {
            for (CovidData covidData : dateEntity.getCovidData()) {
                String stateId = covidData.getStateEntity().getStateName();
                if (statesId.contains(stateId)) {
                    List<Double> covidValues = statesReport.computeIfAbsent(stateId, k -> new ArrayList<>());
                    statesReport.put(stateId, graph.calculate(covidValues, covidDataFunction.applyAsInt(covidData), stateId));
                }
            }
        }

        dataApi.setDatasets(new ArrayList<>());
        statesReport.entrySet()
                .forEach(x -> dataApi.getDatasets().add(new DataSet(x.getValue(), valueOf(x.getKey()))));
        return dataApi;
    }

}
