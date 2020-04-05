package com.janglada.covid2.repository;

import com.janglada.covid2.model.DateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DateRepository extends JpaRepository<DateEntity, String> {

    DateEntity findByDate(LocalDate localDate);
}
