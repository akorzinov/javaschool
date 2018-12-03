package com.korzinov.dao;

import com.korzinov.entities.*;
import com.korzinov.models.FindTrain;
import com.korzinov.models.RouteModel;

import java.util.Date;
import java.util.List;

public interface ScheduleDao {

    List<FindTrain> findTrainsForUser(String depStation, String destStation, Date date);

    List<FindTrain> findScheduleByStation(String station);

    List<RouteModel> findSchedule(String trainName, Date date);

    List<ScheduleEntity> findScheduleByTrain(TrainEntity train);

    void addListSchedules(List<ScheduleEntity> listScedules);

    void updateSchedule(ScheduleEntity schedule);

    void deleteSchedules(List<ScheduleEntity> listSchedules);

    void updateFreeSeats(List<ScheduleEntity> listSchedule);

    List<FindTrain> findTrainDetails(String trainName);

    List<FindTrain> findTrainDetailsUnique(String trainName);

    List<FindTrain> findTrainDetailsAll();

    List<ScheduleEntity> findScheduleByListId(List<Integer> listSchedulesId);

    List<Integer> findSchedulesIdByScheduleId(int scheduleId);

    ScheduleEntity findScheduleByRouteIdAndDate(RouteEntity route, Date date);

    List<ScheduleEntity> findScheduleByStationAndDate(String station, Date date);

    List<FindTrain> findSchedulesIdBySchedule(ScheduleEntity schedule);

}
