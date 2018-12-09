package com.korzinov.models;

import com.korzinov.entities.StationEntity;

public class StationModel {
    private int stationId;
    private String stationName;

    public StationModel() {
    }

    public StationModel(int stationId, String stationName) {
        this.stationId = stationId;
        this.stationName = stationName;
    }

    public StationModel(StationEntity station) {
        this.stationId = station.getStationId();
        this.stationName = station.getStationName();
    }

    public StationModel(StationModel station) {
        this.stationId = station.getStationId();
        this.stationName = station.getStationName();
    }


    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Override
    public String toString() {
        return "StationModel{" +
                "stationId=" + stationId +
                ", stationName='" + stationName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StationModel that = (StationModel) o;

        return stationId == that.stationId;
    }

    @Override
    public int hashCode() {
        return stationId;
    }
}
