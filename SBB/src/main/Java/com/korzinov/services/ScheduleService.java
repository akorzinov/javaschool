package com.korzinov.services;

import com.korzinov.entities.ScheduleEntity;
import com.korzinov.models.*;
import org.primefaces.event.RowEditEvent;
import java.util.Date;
import java.util.List;


public interface ScheduleService {

    List<FindTrain> findTrainsForUser(String depStation, String destStation, Date date);

    List<FindTrain> findScheduleByStation(String station);

    List<RouteModel> findSchedule(String trainName, Date date);

    List<TrainModel> findRouteTrain(String trainName);

    List<RouteModel> addSchedule(List<RouteModel> listSchedules);

    void updateSchedule(RowEditEvent event);

    void deleteSchedule(RouteModel rm, List<RouteModel> listRm);

    List<ScheduleEntity> convertListSchedules(List<RouteModel> listSchedules);

    ScheduleEntity convertRouteModel(RouteModel route);

    List<String> nameStationSuggestions(String stationName);

    List<String> nameTrainSuggestions(String trainName);
}
