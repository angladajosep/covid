package com.janglada.covid2.startup;

import com.janglada.covid2.service.charge.ChargeDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppStartupRunner implements ApplicationRunner {

    private final ChargeDataService chargeData;

    public AppStartupRunner(final ChargeDataService chargeData) {
        this.chargeData = chargeData;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {

            chargeData.chargeData();
        } catch (Exception e) {
            log.warn("End chargin data");
        }
    }

}