package com.korzinov.dao;

import com.korzinov.entities.*;
import com.korzinov.models.FindTrain;
import com.korzinov.models.RouteModel;

import java.util.Date;
import java.util.List;

public interface ScheduleDao {

    List<FindTrain> findTrainsForUser(String depStation, String destStation, Date date);

    List<FindTrain> findScheduleByStation(String station);

    List<RouteModel> findRoute(String trainName);

    List<ScheduleEntity> findScheduleByTrain(TrainEntity train);

    void addRoute(ScheduleEntity schedule);

    void updateRoute(ScheduleEntity schedule);

    void deleteRoute(ScheduleEntity schedule);

    void updateFreeSeats(List<ScheduleEntity> listSchedule);

    List<FindTrain> findTrainDetails(String trainName);

    List<FindTrain> findTrainDetailsUnique(String trainName);

    List<FindTrain> findTrainDetailsAll();
}
