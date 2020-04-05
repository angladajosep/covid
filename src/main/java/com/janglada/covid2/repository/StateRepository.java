package com.janglada.covid2.repository;

import com.janglada.covid2.model.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, String> {

    StateEntity findByStateName(String name);
}
