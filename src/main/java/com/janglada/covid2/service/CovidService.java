package com.janglada.covid2.service;

import com.janglada.covid2.model.CovidData;
import com.janglada.covid2.model.DateEntity;
import com.janglada.covid2.model.api.DataApi;
import com.janglada.covid2.model.api.DataSet;
import com.janglada.covid2.repository.CovidDataRepository;
import com.janglada.covid2.service.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import static com.janglada.covid2.model.StateEnum.valueOf;
import static java.lang.Integer.parseInt;
import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@Slf4j
public class CovidService {
    private final ConsumerSanidadService consumerSanidadService;

    private final CovidDataRepository covidDataRepository;

    private final DateService dateService;
    private final StateService stateService;

    public static final ToIntFunction<CovidData> CASE_VALUE = CovidData::getCasos;
    public static final ToIntFunction<CovidData> DEATH_VALUE = CovidData::getFallecidos;


    public CovidService(final ConsumerSanidadService consumerSanidadService, final CovidDataRepository covidDataRepository,
                        final DateService dateService, final StateService stateService) {
        this.consumerSanidadService = consumerSanidadService;
        this.covidDataRepository = covidDataRepository;
        this.dateService = dateService;
        this.stateService = stateService;
    }

    public List<CovidData> getValues() {
        return covidDataRepository.findAll();
    }

    public List<DateEntity> getDateEntityValues() {
        return dateService.findAll();
    }

    public DataApi getApiValues(ToIntFunction<CovidData> index, List<String> statesId) {
        List<DateEntity> dateEntityList = dateService.findAll();

        DataApi dataApi = new DataApi();
        dataApi.setLabels(dateEntityList.stream().map(x-> x.getDate().toString()).collect(Collectors.toList()));

        Map<String, List<Integer>> statesReport = new HashMap<>();
        for (DateEntity dateEntity : dateEntityList) {
            for (CovidData covidData : dateEntity.getCovidData()) {

                String stateId = covidData.getStateEntity().getStateName();
                if (statesId.contains(stateId)) {
                    List<Integer> integers = statesReport.computeIfAbsent(stateId, k -> new ArrayList<>());
                    integers.add(index.applyAsInt(covidData));
                    statesReport.put(stateId, integers);
                }
            }
        }

        dataApi.setDatasets(new ArrayList<>());
        statesReport.entrySet()
                .stream()
                .forEach(x -> dataApi.getDatasets().add(new DataSet(x.getValue(), valueOf(x.getKey()))));
        return dataApi;
    }
    public void chargeData() {
        try {
            File lastFile = consumerSanidadService.getDataFromUrl();
            updateValues(lastFile);
        } catch (MyException e) {
            log.error("Error", e.getMessage());
        }
    }

    private void updateValues(File csvFile) throws MyException {

        try {
            String report = FileUtils.readFileToString(csvFile, UTF_8);
            Reader in = new StringReader(report);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);

            CovidData covidData;
            int row = 1;
            for (CSVRecord record : records) {
                if (row != 1) {
                    covidData = new CovidData();
                    covidData.setStateEntity(stateService.saveState(record.get(0)));
                    covidData.setDateEntity(dateService.saveDate(record.get(1)));
                    covidData.setCasos(getIntValue(record, 2));
                    covidData.setHospitalizados(getIntValue(record, 3));
                    covidData.setUCI(getIntValue(record, 4));
                    covidData.setFallecidos(getIntValue(record, 5));
                    covidData.setRecuperados(getIntValue(record, 6));

                    covidDataRepository.save(covidData);
                }
                row++;
            }

        } catch (IOException exception) {
            log.error("Error ", exception.getMessage());
            throw new MyException(exception.getMessage());
        }
    }

    private int getIntValue(CSVRecord record, int i) {
        return !record.get(i).isEmpty() ? parseInt(record.get(i)) : 0;
    }
}
