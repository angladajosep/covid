package com.janglada.covid2.service.consumer;

import com.janglada.covid2.service.exception.MyException;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Service
public class ConsumerSanidadService {

    public File getDataFromUrl() throws MyException {
        try {
            URL url = new URL("https://covid19.isciii.es/resources/serie_historica_acumulados.csv");
            File destination = new File("serie_historica_acumulados.csv");
            FileUtils.copyURLToFile(url, destination);
            return destination;
        } catch (IOException e) {
            throw new MyException("error getting file");
        }
    }




}
