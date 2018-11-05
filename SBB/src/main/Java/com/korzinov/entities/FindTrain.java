package com.korzinov.entities;

import java.util.Date;

public class FindTrain {

    private String trainName;
    private String stationName;
    private Date departureTime;
    private Date arrivalTime;
    private int freeSeats;
    private int orderStation;
    private String stationDest;
    private Date currentTime = new Date(System.currentTimeMillis() + 600000L);

    public FindTrain() {
    }

    public FindTrain(String trainName, String stationName, Date departureTime, Date arrivalTime, int freeSeats, int orderStation) {
        this.trainName = trainName;
        this.stationName = stationName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.freeSeats = freeSeats;
        this.orderStation = orderStation;
    }

    public FindTrain(String trainName, Date arrivalTime, Date departureTime) {
        this.trainName = trainName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
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

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    public int getOrderStation() {
        return orderStation;
    }

    public void setOrderStation(int orderStation) {
        this.orderStation = orderStation;
    }

    public String getStationDest() {
        return stationDest;
    }

    public void setStationDest(String stationDest) {
        this.stationDest = stationDest;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return "FindTrain{"  +
               "\n\t" + "trainName='" + trainName + '\'' +
                "\n\t" + ", stationName='" + stationName + '\'' +
                "\n\t" + ", departureTime=" + departureTime +
                "\n\t" + ", arrivalTime=" + arrivalTime +
                "\n\t" + ", freeSeats=" + freeSeats +
                "\n\t" + ", orderStation=" + orderStation +
                "\n\t" + ", stationDest=" + stationDest +
                '}';
    }

}
