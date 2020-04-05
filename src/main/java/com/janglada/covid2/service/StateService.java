package com.janglada.covid2.service;

import com.janglada.covid2.model.StateEntity;
import com.janglada.covid2.repository.StateRepository;
import org.springframework.stereotype.Service;

@Service
public class StateService {

    private final StateRepository repository;

    public StateService(final StateRepository repository) {
        this.repository = repository;
    }

    public StateEntity saveState(final String stateName) {

        StateEntity state = repository.findByStateName(stateName);

        if (null != state) {
            return state;
        } else {
            StateEntity stateEntity = new StateEntity();
            stateEntity.setStateName(stateName);
            return repository.save(stateEntity);
        }

    }

}
