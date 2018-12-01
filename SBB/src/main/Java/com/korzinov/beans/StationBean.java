package com.korzinov.beans;

import com.korzinov.models.StationModel;
import org.springframework.context.annotation.Scope;
import javax.inject.Named;
import java.util.List;

@Named(value = "stationBean")
@Scope("session")
public class StationBean {

    private String stationNameForSearch;
    private List<StationModel> listStation;
    private StationModel station = new StationModel();

    public String getStationNameForSearch() {
        return stationNameForSearch;
    }

    public void setStationNameForSearch(String stationNameForSearch) {
        this.stationNameForSearch = stationNameForSearch;
    }

    public List<StationModel> getListStation() {
        return listStation;
    }

    public void setListStation(List<StationModel> listStation) {
        this.listStation = listStation;
    }

    public StationModel getStation() {
        return station;
    }

    public void setStation(StationModel station) {
        this.station = station;
    }
}
