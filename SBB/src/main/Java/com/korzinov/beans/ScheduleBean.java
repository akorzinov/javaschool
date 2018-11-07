package com.korzinov.beans;

import com.korzinov.entities.FindTrain;
import com.korzinov.entities.RouteModel;
import com.korzinov.entities.ScheduleEntity;
import com.korzinov.entities.TrainEntity;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Named(value = "scheduleBean")
public class ScheduleBean {

    private String depStation;
    private String destStation;
    private Date date = Calendar.getInstance().getTime();
    private List<FindTrain> listFoundTrains = new ArrayList<>();
    private String station;
    private List<FindTrain> listSchedule = new ArrayList<>();
    private String trainName;
    private List<RouteModel> listRoute = new ArrayList<>();
    private ScheduleEntity schedule = new ScheduleEntity();
    private TrainEntity train = new TrainEntity();
    private String stationName;
    private List<TrainEntity> listTrain = new ArrayList<>();

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

    public List<TrainEntity> getListTrain() {
        return listTrain;
    }

    public void setListTrain(List<TrainEntity> listTrain) {
        this.listTrain = listTrain;
    }
}
