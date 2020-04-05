package com.janglada.covid2.service;

import com.janglada.covid2.model.DateEntity;
import com.janglada.covid2.repository.DateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class DateService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");

    private final DateRepository repository;

    public DateService(final DateRepository dateRepository) {
        this.repository = dateRepository;
    }


    public List<DateEntity> findAll() {
        log.info("findAll");
        return repository.findAll();
    }


    public DateEntity saveDate(String stringDate) {

        LocalDate date = LocalDate.parse(stringDate, DATE_TIME_FORMATTER);
        DateEntity byDate = repository.findByDate(date);

        if (null != byDate) {
            return byDate;
        } else {
            DateEntity dateEntity = new DateEntity();
            dateEntity.setDate(date);
            return repository.save(dateEntity);

        }

    }
}
