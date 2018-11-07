package com.korzinov.beans;


import com.korzinov.entities.StationEntity;

import javax.inject.Named;
import java.util.List;

@Named(value = "stationBean")
public class StationBean {

    private String stationNameForSearch;
    private List<StationEntity> listStation;
    private StationEntity station = new StationEntity();

    public String getStationNameForSearch() {
        return stationNameForSearch;
    }

    public void setStationNameForSearch(String stationNameForSearch) {
        this.stationNameForSearch = stationNameForSearch;
    }

    public List<StationEntity> getListStation() {
        return listStation;
    }

    public void setListStation(List<StationEntity> listStation) {
        this.listStation = listStation;
    }

    public StationEntity getStation() {
        return station;
    }

    public void setStation(StationEntity station) {
        this.station = station;
    }
}
