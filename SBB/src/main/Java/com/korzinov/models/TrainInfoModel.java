package com.korzinov.models;

import java.util.Date;

public class TrainInfoModel implements Comparable<TrainInfoModel> {

    private int scheduleId;
    private String trainName;
    private String stationDep;
    private String stationDest;
    private Date arrivalTime;
    private Date departureTime;
    private String status;
    private String stationName;

    public TrainInfoModel() {
    }

    public TrainInfoModel(FindTrain train) {
        this.scheduleId = train.getScheduleId();
        this.trainName = train.getTrainName();
        this.stationDep = train.getStationName();
        this.stationDest = train.getStationDest();
        this.arrivalTime = train.getArrivalTime();
        this.departureTime = train.getDepartureTime();
        this.status = "On time";
    }

    public TrainInfoModel(RouteModel route) {
        this.stationName = route.getStationName();
        this.trainName = route.getTrainName();
        this.arrivalTime = route.getArrivalTime();
        this.departureTime = route.getDepartureTime();

    }

    public TrainInfoModel(String stationDep, String stationDest, Date arrivalTime, Date departureTime, String status, String stationName) {
        this.stationDep = stationDep;
        this.stationDest = stationDest;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.status = status;
        this.stationName = stationName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
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
                ", status='" + status + '\'' +
                ", stationName='" + stationName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainInfoModel that = (TrainInfoModel) o;

        if (scheduleId != that.scheduleId) return false;
        if (trainName != null ? !trainName.equals(that.trainName) : that.trainName != null) return false;
        if (stationDep != null ? !stationDep.equals(that.stationDep) : that.stationDep != null) return false;
        if (stationDest != null ? !stationDest.equals(that.stationDest) : that.stationDest != null) return false;
        if (arrivalTime != null ? !arrivalTime.equals(that.arrivalTime) : that.arrivalTime != null) return false;
        if (departureTime != null ? !departureTime.equals(that.departureTime) : that.departureTime != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return stationName != null ? stationName.equals(that.stationName) : that.stationName == null;
    }

    @Override
    public int hashCode() {
        int result = scheduleId;
        result = 31 * result + (trainName != null ? trainName.hashCode() : 0);
        result = 31 * result + (stationDep != null ? stationDep.hashCode() : 0);
        result = 31 * result + (stationDest != null ? stationDest.hashCode() : 0);
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (stationName != null ? stationName.hashCode() : 0);
        return result;
    }
}
