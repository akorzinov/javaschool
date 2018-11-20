package com.korzinov.models;

import java.util.Date;

public class TicketTableModel {

    private int id;
    private String trainName;
    private String departureStationName;
    private String destinationStationName;
    private Date departureTime;
    private Date arrivalTime;
    private String firstName;
    private String lastName;
    private Date birthday;

    public TicketTableModel() {}

    public TicketTableModel(TicketTableModel t) {
        this.id = t.id;
        this.trainName = t.trainName;
        this.departureStationName = t.departureStationName;
        this.destinationStationName = t.destinationStationName;
        this.departureTime = t.departureTime;
        this.arrivalTime = t.arrivalTime;
        this.firstName = t.firstName;
        this.lastName = t.lastName;
        this.birthday = t.birthday;
    }

    public TicketTableModel(int id, String trainName, String departureStationName, String destinationStationName, Date departureTime, Date arrivalTime, String firstName, String lastName, Date birthday) {
        this.id = id;
        this.trainName = trainName;
        this.departureStationName = departureStationName;
        this.destinationStationName = destinationStationName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public TicketTableModel(int id, String firstName, String lastName, Date birthday) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TicketTableModel{" +
                "id=" + id +
                ", trainName='" + trainName + '\'' +
                ", departureStationName='" + departureStationName + '\'' +
                ", destinationStationName='" + destinationStationName + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketTableModel that = (TicketTableModel) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + trainName.hashCode();
        result = 31 * result + departureStationName.hashCode();
        result = 31 * result + destinationStationName.hashCode();
        result = 31 * result + departureTime.hashCode();
        result = 31 * result + arrivalTime.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + birthday.hashCode();
        return result;
    }
}
