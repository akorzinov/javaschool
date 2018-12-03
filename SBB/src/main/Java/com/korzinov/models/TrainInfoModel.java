package com.korzinov.models;

import java.util.Date;

public class TrainInfoModel implements Comparable<TrainInfoModel> {

    private int scheduleId;
    private String trainName;
    private String stationDep;
    private String stationDest;
    private Date arrivalTime;
    private Date departureTime;

    public TrainInfoModel() {
    }

    public TrainInfoModel(FindTrain train) {
        this.scheduleId = train.getScheduleId();
        this.trainName = train.getTrainName();
        this.stationDep = train.getStationName();
        this.stationDest = train.getStationDest();
        this.arrivalTime = train.getArrivalTime();
        this.departureTime = train.getDepartureTime();
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getStationDep() {
        return stationDep;
    }

    public void setStationDep(String stationDep) {
        this.stationDep = stationDep;
    }

    public String getStationDest() {
        return stationDest;
    }

    public void setStationDest(String stationDest) {
        this.stationDest = stationDest;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Override
    public int compareTo(TrainInfoModel o) {
        return arrivalTime.compareTo(o.getArrivalTime());
    }

    @Override
    public String toString() {
        return "TrainInfoModel{" +
                "scheduleId=" + scheduleId +
                ", trainName='" + trainName + '\'' +
                ", stationDep='" + stationDep + '\'' +
                ", stationDest='" + stationDest + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                '}';
    }
}
