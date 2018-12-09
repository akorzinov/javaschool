package com.korzinov.services;

import com.korzinov.models.TrainInfoModel;

import java.util.Date;
import java.util.List;

public interface BoardStationService {

    List<TrainInfoModel> listTrainsToBoard(String stationName, Date date);

    List<String> listStations();
}
