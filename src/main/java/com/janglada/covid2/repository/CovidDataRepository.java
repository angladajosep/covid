package com.janglada.covid2.repository;

import com.janglada.covid2.model.CovidData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidDataRepository extends JpaRepository<CovidData, String> {


}
