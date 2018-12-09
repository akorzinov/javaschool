package com.korzinov.models;

import com.korzinov.entities.RouteEntity;

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
    private int scheduleId;
    private int freeSeats;
    private Integer scheduleIdLast;
    private RouteEntity routeByRouteId;

    public RouteModel() {
    }

    public RouteModel(int scheduleId, int freeSeats, String trainName, int quantitySeats, Integer orderStation, String stationName, Date arrivalTime, Date departureTime) {
        this.trainName = trainName;
        this.quantitySeats = quantitySeats;
        this.orderStation = orderStation;
        this.stationName = stationName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.scheduleId = scheduleId;
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

    public RouteModel(int scheduleId, RouteEntity routeByRouteId, Integer scheduleIdLast, int freeSeats, String trainName, Integer orderStation, String stationName, Date arrivalTime, Date departureTime) {
        this.scheduleId = scheduleId;
        this.routeByRouteId = routeByRouteId;
        this.scheduleIdLast = scheduleIdLast;
        this.freeSeats = freeSeats;
        this.trainName = trainName;
        this.orderStation = orderStation;
        this.stationName = stationName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public RouteModel(RouteModel route) {
        this.routeId = route.getRouteId();
        this.trainName = route.getTrainName();
        this.quantitySeats = route.getQuantitySeats();
        this.orderStation = route.getOrderStation();
        this.stationName = route.getStationName();
        this.arrivalTime = route.getArrivalTime();
        this.departureTime = route.getDepartureTime();
        this.stationId = route.getStationId();
        this.trainId = route.getTrainId();
        this.scheduleId = route.getScheduleId();
        this.freeSeats = route.getFreeSeats();
        this.scheduleIdLast = route.getScheduleIdLast();
        this.routeByRouteId = route.getRouteByRouteId();
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

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
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

    public RouteEntity getRouteByRouteId() {
        return routeByRouteId;
    }

    public void setRouteByRouteId(RouteEntity routeByRouteId) {
        this.routeByRouteId = routeByRouteId;
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
                ", scheduleId=" + scheduleId +
                ", freeSeats=" + freeSeats +
                ", scheduleIdLast=" + scheduleIdLast +
                ", routeByRouteId=" + routeByRouteId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteModel that = (RouteModel) o;

        return routeId == that.routeId;
    }

    @Override
    public int hashCode() {
        return routeId;
    }
}
