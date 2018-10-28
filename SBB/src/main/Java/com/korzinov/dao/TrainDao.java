package com.korzinov.dao;

import com.korzinov.entities.TrainEntity;

import java.util.List;

public interface TrainDao {

    List<TrainEntity> findByNameTrain(String nameTrain);

    void addTrain(TrainEntity tr);

    void updateTrain(TrainEntity tr);

    void deleteTrain(TrainEntity tr);
}
