package com.korzinov.models;

import java.util.Date;

public class FindTrain implements Comparable<FindTrain> {

    private String trainName;
    private String stationName;
    private Date departureTime;
    private Date arrivalTime;
    private int freeSeats;
    private int orderStation;
    private String stationDest;
    private Date currentTime = new Date(System.currentTimeMillis() + 600000L);
    private int scheduleIdDep;
    private int scheduleId;
    private Integer scheduleIdLast;

    public FindTrain() {
    }

    public FindTrain(String trainName, String stationName, Date departureTime, Date arrivalTime, int freeSeats, int orderStation, int scheduleIdDep) {
        this.trainName = trainName;
        this.stationName = stationName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.freeSeats = freeSeats;
        this.orderStation = orderStation;
        this.scheduleIdDep = scheduleIdDep;
    }

    public FindTrain(String trainName, String stationName, Date departureTime, Date arrivalTime, int freeSeats, int orderStation, String stationDest, Date currentTime, int scheduleIdDep) {
        this.trainName = trainName;
        this.stationName = stationName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.freeSeats = freeSeats;
        this.orderStation = orderStation;
        this.stationDest = stationDest;
        this.currentTime = currentTime;
        this.scheduleIdDep = scheduleIdDep;
    }

    public FindTrain(String trainName, Date arrivalTime, Date departureTime) {
        this.trainName = trainName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public FindTrain(String trainName, String stationName, int orderStation) {
        this.trainName = trainName;
        this.stationName = stationName;
        this.orderStation = orderStation;
    }

    public FindTrain(int scheduleId, Integer scheduleIdLast, String trainName, int orderStation, String stationName, Date arrivalTime, Date departureTime) {
        this.scheduleId = scheduleId;
        this.scheduleIdLast = scheduleIdLast;
        this.trainName = trainName;
        this.orderStation = orderStation;
        this.stationName = stationName;
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

    public int getScheduleIdDep() {
        return scheduleIdDep;
    }

    public void setScheduleIdDep(int scheduleIdDep) {
        this.scheduleIdDep = scheduleIdDep;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getScheduleIdLast() {
        return scheduleIdLast;
    }

    public void setScheduleIdLast(Integer scheduleIdLast) {
        this.scheduleIdLast = scheduleIdLast;
    }

    @Override
    public String toString() {
        return "FindTrain{" +
                "trainName='" + trainName + '\'' +
                ", stationName='" + stationName + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", freeSeats=" + freeSeats +
                ", orderStation=" + orderStation +
                ", stationDest='" + stationDest + '\'' +
                ", currentTime=" + currentTime +
                ", scheduleIdDep=" + scheduleIdDep +
                ", scheduleId=" + scheduleId +
                ", scheduleIdLast=" + scheduleIdLast +
                '}';
    }

    @Override
    public int compareTo(FindTrain o) {
        return departureTime.compareTo(o.getDepartureTime());
    }
}
