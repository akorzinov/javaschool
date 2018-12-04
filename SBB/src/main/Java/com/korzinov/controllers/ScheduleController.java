package com.korzinov.controllers;

import com.korzinov.beans.ScheduleBean;
import com.korzinov.dao.RouteDao;
import com.korzinov.models.FindTrain;
import com.korzinov.models.RouteModel;
import com.korzinov.models.TrainModel;
import com.korzinov.services.*;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
    private RouteService routeService;

    @Autowired
    private ScheduleBean scheduleBean;

    @Autowired
    private MQService mqService;

    public void findTrain() {
        scheduleBean.setListFoundTrains(scheduleService.findTrainsForUser(scheduleBean.getDepStation(), scheduleBean.getDestStation(), scheduleBean.getDate()));
    }

    public void ScheduleByStation() {
        scheduleBean.setListSchedule(scheduleService.findScheduleByStation(scheduleBean.getStation()));
        scheduleBean.setRenderFoundStationTable(true);
        scheduleBean.setRenderTrainDetails(false);
        scheduleBean.setRenderBackButton(false);
    }

    public void findSchedule() {
        scheduleBean.setRenderConfigScheduleTable2(false);
        scheduleBean.setRenderConfigScheduleTable1(true);
        scheduleBean.setListSchedules(scheduleService.findSchedule(scheduleBean.getTrainName(), scheduleBean.getDate()));
    }

    public void loadRoutes() {
        scheduleBean.setListSchedules(routeService.loadSchedules(scheduleBean.getTrainNameRoute()));
        scheduleBean.setRenderConfigScheduleTable2(true);
        scheduleBean.setRenderConfigScheduleTable1(false);
    }

    public void addSchedule() {
        scheduleBean.setListSchedules(scheduleService.addSchedule(scheduleBean.getListSchedules()));
        scheduleBean.setRenderConfigScheduleTable2(false);
        scheduleBean.setRenderConfigScheduleTable1(true);
    }

    public void editSchedule(RowEditEvent event) {
        scheduleService.updateSchedule(event);
        mqService.send(event, scheduleBean.getListSchedules());
    }

    public void deleteScheduleDb(RouteModel rm) {
        scheduleService.deleteSchedule(rm, scheduleBean.getListSchedules());
        mqService.sendCanceled(rm, scheduleBean.getListSchedules());
    }

    public String message(RouteModel rm) {
        int index = scheduleBean.getListSchedules().indexOf(rm);
        if (index == 0) {
            return "All schedule will be deleted, continue?";
        } else if (index == 1) {
            return "Schedule with 1 station incorrect, that's why all schedule will be also deleted, continue?";
        } else if (index == scheduleBean.getListSchedules().size() - 1) {
            return "Record with " + rm.getStationName() + " station will be deleted, continue?";
        }
        return "The following stations behind " + rm.getStationName() +" will be also deleted, continue?";
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
//        scheduleBean.setListScheduleTrain(scheduleService.findRoute(train.getTrainName()));
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

    public MQService getMqService() {
        return mqService;
    }

    public void setMqService(MQService mqService) {
        this.mqService = mqService;
    }

    public RouteService getRouteService() {
        return routeService;
    }

    public void setRouteService(RouteService routeService) {
        this.routeService = routeService;
    }
}
