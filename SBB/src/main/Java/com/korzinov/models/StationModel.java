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
}
