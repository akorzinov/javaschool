package com.korzinov.services;

import com.korzinov.entities.TrainEntity;
import org.primefaces.event.RowEditEvent;

public interface TrainService {

    void findByNameTrain();

    void addTrain();

    void updateTrain(RowEditEvent event);

    void deleteTrain(TrainEntity tr);
}
