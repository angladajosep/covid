package com.janglada.covid2.service.charge;

import com.janglada.covid2.model.CovidData;
import com.janglada.covid2.model.StateEnum;
import com.janglada.covid2.repository.CovidDataRepository;
import com.janglada.covid2.service.DateService;
import com.janglada.covid2.service.StateService;
import com.janglada.covid2.service.consumer.ConsumerSanidadService;
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

import static java.lang.Integer.parseInt;
import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@Slf4j
public class ChargeDataService {

    private final ConsumerSanidadService consumerSanidadService;
    private final CovidDataRepository covidDataRepository;
    private final StateService stateService;
    private final DateService dateService;

    public ChargeDataService(final ConsumerSanidadService consumerSanidadService,
                             final CovidDataRepository covidDataRepository,
                             final StateService stateService,
                             final DateService dateService) {
        this.consumerSanidadService = consumerSanidadService;
        this.covidDataRepository = covidDataRepository;
        this.stateService = stateService;
        this.dateService = dateService;
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
                if ((row != 1) && (null != StateEnum.valueOf(record.get(0)))) {
                    covidData = new CovidData();
                    covidData.setStateEntity(stateService.saveState(record.get(0)));
                    covidData.setDateEntity(dateService.saveDate(record.get(1)));
                    covidData.setCaseCumulative(getIntValue(record, 2));
                    covidData.setHospitalCumulative(getIntValue(record, 3));
                    covidData.setUciCumulative(getIntValue(record, 4));
                    covidData.setDeathCumulative(getIntValue(record, 5));
                    covidData.setRecoveredCumulative(getIntValue(record, 6));
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
