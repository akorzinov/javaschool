package com.korzinov.services;

import com.korzinov.entities.TrainEntity;

import java.util.List;

public interface TrainService {

    List<TrainEntity> findByNameTrain(String train);

    void addTrain(TrainEntity tr);

    void updateTrain(TrainEntity tr);

    void deleteTrain(TrainEntity tr);
}
