package com.korzinov.models;

import java.util.Date;

public class RouteModel {

    private int routeId;
    private String trainName;
    private int quantitySeats;
    private Integer orderStation;
    private String stationName;
    private Date arrivalTime;
    private Date departureTime;
    private int stationId;
    private int trainId;
    private int recordId;
    private int freeSeats;
    private Integer scheduleIdLast;

    public RouteModel() {
    }

    public RouteModel(int recordId,int freeSeats, String trainName, int quantitySeats, Integer orderStation, String stationName, Date arrivalTime, Date departureTime) {
        this.trainName = trainName;
        this.quantitySeats = quantitySeats;
        this.orderStation = orderStation;
        this.stationName = stationName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.recordId = recordId;
        this.freeSeats = freeSeats;
    }

    public RouteModel(int routeId, Integer orderStation, int stationId, int trainId) {
        this.routeId = routeId;
        this.orderStation = orderStation;
        this.stationId = stationId;
        this.trainId = trainId;
    }

    public RouteModel(int routeId, String trainName, Integer orderStation, String stationName) {
        this.routeId = routeId;
        this.trainName = trainName;
        this.orderStation = orderStation;
        this.stationName = stationName;
    }

    public RouteModel(int routeId, String trainName, Integer orderStation, String stationName, Date arrivalTime, Date departureTime) {
        this.routeId = routeId;
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

    public int getQuantitySeats() {
        return quantitySeats;
    }

    public void setQuantitySeats(int quantitySeats) {
        this.quantitySeats = quantitySeats;
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

    public Integer getOrderStation() {
        return orderStation;
    }

    public void setOrderStation(Integer orderStation) {
        this.orderStation = orderStation;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public Integer getScheduleIdLast() {
        return scheduleIdLast;
    }

    public void setScheduleIdLast(Integer scheduleIdLast) {
        this.scheduleIdLast = scheduleIdLast;
    }

    @Override
    public String toString() {
        return "RouteModel{" +
                "routeId=" + routeId +
                ", trainName='" + trainName + '\'' +
                ", quantitySeats=" + quantitySeats +
                ", orderStation=" + orderStation +
                ", stationName='" + stationName + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", stationId=" + stationId +
                ", trainId=" + trainId +
                ", recordId=" + recordId +
                ", freeSeats=" + freeSeats +
                ", scheduleIdLast=" + scheduleIdLast +
                '}';
    }
}
