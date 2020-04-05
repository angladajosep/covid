package com.janglada.covid2.api;

import com.janglada.covid2.model.StateEnum;
import com.janglada.covid2.model.api.ComboApi;
import com.janglada.covid2.model.api.DataApi;
import com.janglada.covid2.model.api.DataSet;
import com.janglada.covid2.service.CovidService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(CovidController.BASE_MAPPING)
@Slf4j
public class CovidController {
    protected static final String BASE_MAPPING = "/api/covid";

    private final CovidService covidService;

    public CovidController(CovidService covidService) {
        this.covidService = covidService;
    }

    @GetMapping("/up")
    public ResponseEntity updateData() {
        log.info("getCovidValues");
        covidService.chargeData();
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity getCovidValues() {
        log.info("getCovidValues");
        return ResponseEntity.ok(covidService.getDateEntityValues());
    }

    @GetMapping("/dataTest")
    public ResponseEntity getCovidValuesApiTest() {
        log.info("getCovidValues");
        DataApi dataApi = new DataApi();
        dataApi.setLabels(Arrays.asList("1","2"));
        dataApi.setDatasets(Arrays.asList(new DataSet(Arrays.asList(1,2),"CA", "rg"),
                new DataSet(Arrays.asList(3,4),"MD","d")));

        return ResponseEntity.ok(dataApi);
    }

    @GetMapping("/states")
    public ResponseEntity getStates() {
        log.info("getCovidValues");
        return ResponseEntity.ok( Arrays.stream(StateEnum.values())
                .map(x -> new ComboApi(x.getStateName(), x.getId())).collect(Collectors.toList()));
    }


    @GetMapping("/data/case")
    public ResponseEntity<DataApi> getCovidCaseValuesApi(@RequestParam(name = "states") List<String> statesId) {
        log.info("getCovidValues");
        log.info(statesId.toString());
        DataApi dataApi = covidService.getApiValues(CovidService.CASE_VALUE, statesId);
        return ResponseEntity.ok(dataApi);
    }


    @GetMapping("/data/death")
    public ResponseEntity<DataApi> getCovidDeathValuesApi(@RequestParam(name = "states") List<String> statesId) {
        log.info("getCovidValues");
        log.info(statesId.toString());

        DataApi dataApi = covidService.getApiValues(CovidService.DEATH_VALUE, statesId);

        return ResponseEntity.ok(dataApi);
    }


}
