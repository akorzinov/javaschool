package com.korzinov.controllers;

import com.korzinov.entities.FindTrain;
import com.korzinov.entities.RouteModel;
import com.korzinov.entities.ScheduleEntity;
import com.korzinov.entities.TrainEntity;
import com.korzinov.services.ScheduleService;
import com.korzinov.services.StationService;
import com.korzinov.services.TrainService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Named(value = "scheduleController")
@SessionScoped
@Controller
public class ScheduleController implements Serializable{

    static final Logger logger = LogManager.getLogger(ScheduleController.class);
    /*поиск поездов по станции*/
    private String depStation;
    private String destStation;
    private Date date;
    private List<FindTrain> listFoundTrains = new ArrayList<>();
    /*расписание по станции*/
    private String station;
    private List<FindTrain> listSchedule = new ArrayList<>();
    /*редактирование поездов и маршрута*/
    private String trainName;
    private List<RouteModel> listRoute = new ArrayList<>();
    private ScheduleEntity schedule = new ScheduleEntity();
    private TrainEntity train = new TrainEntity();
    private String stationName;
    private List<TrainEntity> listTrain = new ArrayList<>();

    public ScheduleController() {}

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private StationService stationService;

    public void findTrain() {
        listFoundTrains = scheduleService.findTrainsForUser(getDepStation(),getDestStation(),getDate());
    }

    public void ScheduleByStation() {
        listSchedule = scheduleService.findScheduleByStation(getStation());

    }

    public String findRoute() {
        listRoute = scheduleService.findRoute(trainName);
        listTrain = trainService.findByNameTrain(trainName);
        return null;
    }

    public String addRoute() {
        trainService.addTrain(train);
        schedule.setFreeSeats(trainService.findByNameTrain(train.getTrainName()).get(0).getQuantitySeats());
        schedule.setTrainByTrainId(trainService.findByNameTrain(train.getTrainName()).get(0));
        try {
            schedule.setStationByStationId(stationService.findByNameStation(stationName).get(0));
        } catch (IndexOutOfBoundsException e) {
                logger.error("Entered stationName " + stationName + " doesn't exist, need enter exist stationName");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Station name " + stationName + " doesn't exist, need enter existed station name"));
                return null;
        }
        scheduleService.addRoute(schedule);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Route Successfully added"));
        return null;
    }

    public void editRoute(RowEditEvent event) {
        RouteModel rm = (RouteModel)event.getObject();
        ScheduleEntity sc = new ScheduleEntity();
        sc.setRecordId(rm.getRecordId());
        sc.setTrainByTrainId(trainService.findByNameTrain(rm.getTrainName()).get(0));
        System.out.println("station name " + rm.getStationName());
        try {
            sc.setStationByStationId(stationService.findByNameStation(rm.getStationName()).get(0));
        } catch (IndexOutOfBoundsException e) {
            logger.error("Entered stationName " + stationName + " doesn't exist, need enter exist stationName");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Station name " + stationName + " doesn't exist, need enter existed station name"));
            return;
        }
        sc.setOrderStation(rm.getOrderStation());
        sc.setFreeSeats(rm.getFreeSeats());
        sc.setArrivalTime(rm.getArrivalTime());
        sc.setDepartureTime(rm.getDepartureTime());
        scheduleService.updateRoute(sc);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Route Updated"));
    }

    public String deleteRoute(RouteModel rm) {
        ScheduleEntity sc = new ScheduleEntity();
        sc.setRecordId(rm.getRecordId());
        sc.setTrainByTrainId(trainService.findByNameTrain(rm.getTrainName()).get(0));
        sc.setStationByStationId(stationService.findByNameStation(rm.getStationName()).get(0));
        sc.setOrderStation(rm.getOrderStation());
        sc.setFreeSeats(rm.getFreeSeats());
        sc.setArrivalTime(rm.getArrivalTime());
        sc.setDepartureTime(rm.getDepartureTime());
        scheduleService.deleteRoute(sc);
        listRoute = scheduleService.findRoute(trainName);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Route Deleted"));
        return null;
    }

    public String addTrain() {
        trainService.addTrain(train);
        return null;
    }

    public void editTrain(RowEditEvent event) {
        TrainEntity tr = (TrainEntity)event.getObject();
        trainService.updateTrain(tr);
    }

    public String deleteTrain(TrainEntity tr) {
        trainService.deleteTrain(tr);
        listTrain = trainService.findByNameTrain(trainName);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Train " + train.getTrainName() + " Deleted"));
        return null;
    }

    public String buyTicket() {
        return "buyTickets";
    }

    public String getDepStation() {
        return depStation;
    }

    public void setDepStation(String depStation) {
        this.depStation = depStation;
    }

    public String getDestStation() {
        return destStation;
    }

    public void setDestStation(String destStation) {
        this.destStation = destStation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<FindTrain> getListFoundTrains() {
        return listFoundTrains;
    }

    public void setListFoundTrains(List<FindTrain> listFoundTrains) {
        this.listFoundTrains = listFoundTrains;
    }

    public ScheduleService getScheduleService() {
        return scheduleService;
    }

    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public List<FindTrain> getListSchedule() {
        return listSchedule;
    }

    public void setListSchedule(List<FindTrain> listSchedule) {
        this.listSchedule = listSchedule;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public List<RouteModel> getListRoute() {
        return listRoute;
    }

    public void setListRoute(List<RouteModel> listRoute) {
        this.listRoute = listRoute;
    }

    public TrainService getTrainService() {
        return trainService;
    }

    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    public ScheduleEntity getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleEntity schedule) {
        this.schedule = schedule;
    }

    public TrainEntity getTrain() {
        return train;
    }

    public void setTrain(TrainEntity train) {
        this.train = train;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public StationService getStationService() {
        return stationService;
    }

    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    public List<TrainEntity> getListTrain() {
        return listTrain;
    }

    public void setListTrain(List<TrainEntity> listTrain) {
        this.listTrain = listTrain;
    }
}
