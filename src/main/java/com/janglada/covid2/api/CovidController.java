package com.janglada.covid2.api;

import com.janglada.covid2.model.StateEnum;
import com.janglada.covid2.model.api.ComboApi;
import com.janglada.covid2.model.api.DataApi;
import com.janglada.covid2.service.CovidService;
import com.janglada.covid2.service.graphics.GraphCumulative;
import com.janglada.covid2.service.graphics.GraphNoCumulative;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity getCovidValues() {
        log.info("getCovidValues");
        return ResponseEntity.ok(covidService.getDateEntityValues());
    }

    @GetMapping("/states")
    public ResponseEntity getStates() {
        log.info("getStates");
        return ResponseEntity.ok(Arrays.stream(StateEnum.values())
                .map(x -> new ComboApi(x.getStateName(), x.getId())).collect(Collectors.toList()));
    }

    @GetMapping("/data/caseCumulative")
    public ResponseEntity<DataApi> getCovidCumulativeCaseValuesApi(@RequestParam(name = "states", required = false) List<String> statesId,            
                                                                   @RequestParam(name = "population") boolean population) {
        log.info("getCovidCumulativeCaseValuesApi- {}", statesId);
        DataApi dataApi = covidService.getApiValues(CovidService.CASE_VALUE, statesId, new GraphCumulative(population));
        return ResponseEntity.ok(dataApi);
    }

    @GetMapping("/data/case")
    public ResponseEntity<DataApi> getCovidCaseValuesApi(@RequestParam(name = "states", required = false) List<String> statesId,
                                                         @RequestParam(name = "population") boolean population) {
        log.info("getCovidCaseValuesApi- {}", statesId);
        DataApi dataApi = covidService.getApiValues(CovidService.CASE_VALUE, statesId, new GraphNoCumulative(population));
        return ResponseEntity.ok(dataApi);
    }


    @GetMapping("/data/deathCumulative")
    public ResponseEntity<DataApi> getCovidDeathCumulativeValuesApi(@RequestParam(name = "states", required = false) List<String> statesId,
                                                                    @RequestParam(name = "population") boolean population) {
        log.info("getCovidDeathCumulativeValuesApi- {}", statesId);

        DataApi dataApi = covidService.getApiValues(CovidService.DEATH_VALUE, statesId, new GraphCumulative(population));

        return ResponseEntity.ok(dataApi);
    }

    @GetMapping("/data/death")
    public ResponseEntity<DataApi> getCovidDeathValuesApi(@RequestParam(name = "states", required = false) List<String> statesId,
                                                          @RequestParam(name = "population") boolean population) {
        log.info("getCovidDeathValuesApi- {}", statesId);

        DataApi dataApi = covidService.getApiValues(CovidService.DEATH_VALUE,statesId, new GraphNoCumulative(population));

        return ResponseEntity.ok(dataApi);
    }


    @GetMapping("/data/uciCumulative")
    public ResponseEntity<DataApi> getCovidUciCumulativeValuesApi(@RequestParam(name = "states", required = false) List<String> statesId,
                                                                  @RequestParam(name = "population") boolean population) {
        log.info("getCovidUciCumulativeValuesApi- {}", statesId);
        DataApi dataApi = covidService.getApiValues(CovidService.UCI_VALUE, statesId, new GraphCumulative(population));
        return ResponseEntity.ok(dataApi);
    }

    @GetMapping("/data/uci")
    public ResponseEntity<DataApi> getCovidUciValuesApi(@RequestParam(name = "states", required = false) List<String> statesId,
                                                        @RequestParam(name = "population") boolean population) {
        log.info("getCovidUciValuesApi- {}", statesId);
        DataApi dataApi = covidService.getApiValues(CovidService.UCI_VALUE,statesId, new GraphNoCumulative(population));
        return ResponseEntity.ok(dataApi);
    }

    @GetMapping("/data/hospital")
    public ResponseEntity<DataApi> getCovidHospitalValuesApi(@RequestParam(name = "states", required = false) List<String> statesId,
                                                             @RequestParam(name = "population") boolean population) {
        log.info("getCovidHospitalValuesApi- {}", statesId);
        DataApi dataApi = covidService.getApiValues(CovidService.HOSPITAL_VALUE,statesId, new GraphNoCumulative(population));
        return ResponseEntity.ok(dataApi);
    }

    @GetMapping("/data/hospitalCumulative")
    public ResponseEntity<DataApi> getCovidHospitalCumulativeValuesApi(@RequestParam(name = "states", required = false) List<String> statesId,
                                                                       @RequestParam(name = "population") boolean population) {
        log.info("getCovidHospitalCumulativeValuesApi- {}", statesId);
        DataApi dataApi = covidService.getApiValues(CovidService.HOSPITAL_VALUE, statesId, new GraphCumulative(population));
        return ResponseEntity.ok(dataApi);
    }

    @GetMapping("/data/recoveredCumulative")
    public ResponseEntity<DataApi> getCovidRecoveredCumulativeValuesApi(@RequestParam(name = "states", required = false) List<String> statesId,
                                                                        @RequestParam(name = "population") boolean population) {
        log.info("getCovidRecoveredCumulativeValuesApi- {}", statesId);
        DataApi dataApi = covidService.getApiValues(CovidService.RECOVERED_VALUE, statesId, new GraphCumulative(population));
        return ResponseEntity.ok(dataApi);
    }

    @GetMapping("/data/recovered")
    public ResponseEntity<DataApi> getCovidRecoveredValuesApi(@RequestParam(name = "states", required = false) List<String> statesId,
                                                              @RequestParam(name = "population") boolean population) {
        log.info("getCovidRecoveredValuesApi- {}", statesId);
        DataApi dataApi = covidService.getApiValues(CovidService.RECOVERED_VALUE,statesId, new GraphNoCumulative(population));
        return ResponseEntity.ok(dataApi);
    }

}
