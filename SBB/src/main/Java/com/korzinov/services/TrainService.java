package com.korzinov.services;

import com.korzinov.models.TrainModel;
import org.primefaces.event.RowEditEvent;

public interface TrainService {

    void addTrain(TrainModel train);

    void updateTrain(RowEditEvent event);

    void deleteTrain(TrainModel train);
}
