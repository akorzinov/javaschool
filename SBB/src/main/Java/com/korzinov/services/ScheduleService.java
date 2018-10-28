package com.korzinov.services;

import com.korzinov.entities.FindTrain;
import com.korzinov.entities.RouteModel;
import com.korzinov.entities.ScheduleEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface ScheduleService {

    List<FindTrain> findTrainsForUser(String depStation, String destStation, Date date);

    List<FindTrain> findScheduleByStation(String station);

    List<RouteModel> findRoute(String trainName);

    void addRoute(ScheduleEntity schedule);

    void updateRoute(ScheduleEntity schedule);

    void deleteRoute(ScheduleEntity schedule);
}
