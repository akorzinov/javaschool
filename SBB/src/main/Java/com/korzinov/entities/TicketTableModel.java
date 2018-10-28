package com.korzinov.entities;

import java.util.Date;

public class TicketTableModel {

    private String trainName;
    private String departureStationName;
    private String destinationStationName;
    private Date departureTime;
    private Date arrivalTime;
    private String firstName;
    private String lastName;
    private Date birthday;

    public TicketTableModel() {}

    public TicketTableModel(String trainName, String departureStationName, String destinationStationName, Date departureTime, Date arrivalTime, String firstName, String lastName, Date birthday) {
        this.trainName = trainName;
        this.departureStationName = departureStationName;
        this.destinationStationName = destinationStationName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getDepartureStationName() {
        return departureStationName;
    }

    public void setDepartureStationName(String departureStationName) {
        this.departureStationName = departureStationName;
    }

    public String getDestinationStationName() {
        return destinationStationName;
    }

    public void setDestinationStationName(String destinationStationName) {
        this.destinationStationName = destinationStationName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
