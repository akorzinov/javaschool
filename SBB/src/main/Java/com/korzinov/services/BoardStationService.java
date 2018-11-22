package com.korzinov.services;

import com.korzinov.models.FindTrain;

import java.util.Date;
import java.util.List;

public interface BoardStationService {

    List<FindTrain> listTrainsToBoard();
//    List<FindTrain> listTrainsToBoard(String stationName, Date date);

    FindTrain findTrainDetailsUnique(String trainName);
}
