package com.korzinov.services;

import com.korzinov.models.FindTrain;

import java.util.List;

public interface TrainDetailsService {

    List<FindTrain> findTrainDetails(String trainName);

    List<FindTrain> findTrainDetailsAll();

    FindTrain findTrainDetailsUnique(String trainName);
}
