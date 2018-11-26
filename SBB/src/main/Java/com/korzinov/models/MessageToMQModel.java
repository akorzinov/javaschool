package com.korzinov.models;

import java.util.Date;

public class MessageToMQModel {

    private String trainName;
    private String stationName;
    private Date arrivalTime;
    private Date departureTime;

    public MessageToMQModel() {
    }

    public MessageToMQModel(RouteModel rm) {
        this.trainName = rm.getTrainName();
        this.stationName = rm.getStationName();
        this.arrivalTime = rm.getArrivalTime();
        this.departureTime = rm.getDepartureTime();
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
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

    @Override
    public String toString() {
        return "MessageToMQModel{" +
                "trainName='" + trainName + '\'' +
                ", stationName='" + stationName + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                '}';
    }
}
