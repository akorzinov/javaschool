package com.korzinov.services;

import com.korzinov.entities.ScheduleEntity;
import com.korzinov.entities.StationEntity;
import com.korzinov.entities.TrainEntity;
import com.korzinov.models.*;
import org.primefaces.event.RowEditEvent;

import java.util.Date;
import java.util.List;


public interface ScheduleService {

    List<FindTrain> findTrainsForUser(String depStation, String destStation, Date date);

    List<FindTrain> findScheduleByStation(String station);

    List<RouteModel> findRoute(String trainName);

    List<TrainModel> findRouteTrain(String trainName);

//    void addRoute(TrainModel train, ScheduleModel schedule, String stationName);

//    void updateRoute(RowEditEvent event);
//
//    void deleteRoute(RouteModel rm);

//    ScheduleEntity convertScheduleModel(ScheduleModel schedule, String trainName, StationEntity station);

    List<String> nameStationSuggestions(String stationName);

    List<String> nameTrainSuggestions(String trainName);
}
