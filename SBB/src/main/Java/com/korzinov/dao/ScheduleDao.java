package com.korzinov.dao;

import com.korzinov.entities.*;
import com.korzinov.models.FindTrain;
import com.korzinov.models.RouteModel;

import java.util.Date;
import java.util.List;

public interface ScheduleDao {

    List<FindTrain> findTrainsForUser(String depStation, String destStation, Date date);

    List<FindTrain> findScheduleByStation(String station);

    List<FindTrain> findScheduleByStationAndDate(String station, Date date);

    List<RouteModel> findSchedule(String trainName, Date date);

    List<ScheduleEntity> findScheduleByTrain(TrainEntity train);

    void addListSchedules(List<ScheduleEntity> listScedules);

    void addRoute(ScheduleEntity schedule);

    void updateSchedule(ScheduleEntity schedule);

    void deleteRoute(ScheduleEntity schedule);

    void updateFreeSeats(List<ScheduleEntity> listSchedule);

    List<FindTrain> findTrainDetails(String trainName);

    List<FindTrain> findTrainDetailsUnique(String trainName);

    List<FindTrain> findTrainDetailsAll();

    List<ScheduleEntity> findScheduleByListId(List<Integer> listSchedulesId);

    List<Integer> findSchedulesIdByScheduleId(int scheduleId);

    ScheduleEntity findScheduleByRouteIdAndDate(RouteEntity route, Date date);

}
