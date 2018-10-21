package com.korzinov.dao;

import com.korzinov.entities.TrainEntity;

public interface TrainDao {

    TrainEntity findByNameTrain(String nameTrain);
}
