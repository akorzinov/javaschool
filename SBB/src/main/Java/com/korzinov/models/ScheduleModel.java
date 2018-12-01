package com.korzinov.models;

import java.util.Date;

public class ScheduleModel {
    private int recordId;
    private Integer orderStation;
    private int freeSeats;
    private Date arrivalTime;
    private Date departureTime;

    public ScheduleModel() {
    }

    public ScheduleModel(int recordId, Integer orderStation, int freeSeats, Date arrivalTime, Date departureTime) {
        this.recordId = recordId;
        this.orderStation = orderStation;
        this.freeSeats = freeSeats;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public Integer getOrderStation() {
        return orderStation;
    }

    public void setOrderStation(Integer orderStation) {
        this.orderStation = orderStation;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
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
}
