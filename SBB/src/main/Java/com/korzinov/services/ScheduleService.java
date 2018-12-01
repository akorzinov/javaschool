package com.korzinov.services;

import com.korzinov.entities.RouteEntity;
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

    List<RouteModel> findSchedule(String trainName, Date date);

    List<TrainModel> findRouteTrain(String trainName);

    void addSchedule(List<RouteModel> listSchedules);

//    void updateRoute(RowEditEvent event);
//
//    void deleteRoute(RouteModel rm);

    List<ScheduleEntity> convertListSchedules(List<RouteModel> listSchedules);

    List<String> nameStationSuggestions(String stationName);

    List<String> nameTrainSuggestions(String trainName);
}
