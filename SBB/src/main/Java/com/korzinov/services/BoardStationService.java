package com.korzinov.services;

import com.korzinov.models.FindTrain;
import com.korzinov.models.TrainInfoModel;

import java.util.Date;
import java.util.List;

public interface BoardStationService {

    List<TrainInfoModel> listTrainsToBoard();
//    List<TrainInfoModel> listTrainsToBoard(String stationName, Date date);

    FindTrain findTrainDetailsUnique(String trainName);
}
