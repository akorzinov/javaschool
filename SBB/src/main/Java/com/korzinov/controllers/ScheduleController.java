package com.korzinov.controllers;

import com.korzinov.models.RouteModel;
import com.korzinov.entities.TrainEntity;
import com.korzinov.services.ScheduleService;
import com.korzinov.services.StationService;
import com.korzinov.services.TrainService;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "scheduleController")
@SessionScoped
@Controller
public class ScheduleController implements Serializable{

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private StationService stationService;

    public void findTrain() {
        scheduleService.findTrainsForUser();
    }

    public void ScheduleByStation() {
        scheduleService.findScheduleByStation();

    }

    public String findRoute() {
        scheduleService.findRoute();
        trainService.findByNameTrain();
        return null;
    }

    public String addRoute() {
        scheduleService.addRoute();
        return null;
    }

    public void editRoute(RowEditEvent event) {
        scheduleService.updateRoute(event);
    }

    public String deleteRoute(RouteModel rm) {
        scheduleService.deleteRoute(rm);
        return null;
    }

    public String addTrain() {
        trainService.addTrain();
        return null;
    }

    public void editTrain(RowEditEvent event) {
        trainService.updateTrain(event);
    }

    public String deleteTrain(TrainEntity tr) {
        trainService.deleteTrain(tr);
        return null;
    }

    public String buyTicket() {
        return "buyTickets";
    }

    public ScheduleService getScheduleService() {
        return scheduleService;
    }

    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    public TrainService getTrainService() {
        return trainService;
    }

    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    public StationService getStationService() {
        return stationService;
    }

    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }
}
