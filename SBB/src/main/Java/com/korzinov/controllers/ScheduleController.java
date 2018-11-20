package com.korzinov.controllers;

import com.korzinov.beans.ScheduleBean;
import com.korzinov.models.FindTrain;
import com.korzinov.models.RouteModel;
import com.korzinov.entities.TrainEntity;
import com.korzinov.models.TrainModel;
import com.korzinov.services.ScheduleService;
import com.korzinov.services.StationService;
import com.korzinov.services.TrainService;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

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

    @Autowired
    private ScheduleBean scheduleBean;

    public void findTrain() {
        scheduleBean.setListFoundTrains(scheduleService.findTrainsForUser(scheduleBean.getDepStation(), scheduleBean.getDestStation(), scheduleBean.getDate()));
    }

    public void ScheduleByStation() {
        scheduleBean.setListSchedule(scheduleService.findScheduleByStation(scheduleBean.getStation()));
        scheduleBean.setRenderFoundStationTable(true);
        scheduleBean.setRenderTrainDetails(false);
        scheduleBean.setRenderBackButton(false);
    }

    public String findRoute() {
        scheduleBean.setListRoute(scheduleService.findRoute(scheduleBean.getTrainName()));
        scheduleBean.setListTrain(scheduleService.findRouteTrain(scheduleBean.getTrainName()));
//        trainService.findByNameTrain();
        return null;
    }

    public String addRoute() {
        scheduleService.addRoute(scheduleBean.getTrain(), scheduleBean.getSchedule(), scheduleBean.getStationName());
        scheduleBean.setListTrain(scheduleService.findRouteTrain(scheduleBean.getTrain().getTrainName()));
        scheduleBean.setListRoute(scheduleService.findRoute(scheduleBean.getTrain().getTrainName()));
        return null;
    }

    public void editRoute(RowEditEvent event) {
        scheduleService.updateRoute(event);
    }

    public String deleteRoute(RouteModel rm) {
        scheduleService.deleteRoute(rm);
        scheduleBean.setListRoute(scheduleService.findRoute(scheduleBean.getTrainName())); /*может будет работать и без этого*/
        return null;
    }

    public String addTrain() {
        trainService.addTrain(scheduleBean.getTrain());
        return null;
    }

    public void editTrain(RowEditEvent event) {
        trainService.updateTrain(event);
    }

    public String deleteTrain(TrainModel train) {
        trainService.deleteTrain(train);
        scheduleBean.setListTrain(scheduleService.findRouteTrain(scheduleBean.getTrainName())); /*может будет работать и без этого*/
        return null;
    }

    public String buyTicket() {
        return "buyTickets";
    }

    public List<String> nameStationSuggestions(String stationName){
        return scheduleService.nameStationSuggestions(stationName);
    }

    public List<String> nameTrainSuggestions(String trainName) {
        return scheduleService.nameTrainSuggestions(trainName);
    }

    public void onDblClickRowSelect(SelectEvent event) {
        FindTrain train = (FindTrain)event.getObject();
        scheduleBean.setListScheduleTrain(scheduleService.findRoute(train.getTrainName()));
        scheduleBean.setRenderFoundStationTable(false);
        scheduleBean.setRenderTrainDetails(true);
        scheduleBean.setRenderBackButton(true);
    }

    public void back() {
        scheduleBean.setRenderFoundStationTable(true);
        scheduleBean.setRenderTrainDetails(false);
        scheduleBean.setRenderBackButton(false);
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

    public ScheduleBean getScheduleBean() {
        return scheduleBean;
    }

    public void setScheduleBean(ScheduleBean scheduleBean) {
        this.scheduleBean = scheduleBean;
    }
}
