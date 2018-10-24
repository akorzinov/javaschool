package com.korzinov.controllers;

import com.korzinov.entities.StationEntity;
import com.korzinov.services.StationService;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "stationController")
@SessionScoped
@Controller
public class StationController implements Serializable{

    @Autowired
    private StationService stationService;

    private String stationNameForSearch;
    private List<StationEntity> listStation;
    private StationEntity station = new StationEntity();

    public String FindStation() {
        listStation = stationService.findByNameStation(stationNameForSearch);
        return null;
    }

    public String addStation() {
        stationService.addStation(station);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Station " + station.getStationName() + " Successfully added"));
        return null;
    }

    public void editStation(RowEditEvent event) {
        StationEntity st = (StationEntity)event.getObject();
        stationService.updateStation(st);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Station " + station.getStationName() + " Updated"));
    }

    public String deleteStation(StationEntity st) {
        stationService.deleteStation(st);
        listStation = stationService.findByNameStation(stationNameForSearch);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Station " + st.getStationName() + " Deleted"));
        return null;
    }

    public StationService getStationService() {
        return stationService;
    }

    public void setStationService(StationService stationService) {
        this.stationService = stationService;
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

    public String getStationNameForSearch() {
        return stationNameForSearch;
    }

    public void setStationNameForSearch(String stationNameForSearch) {
        this.stationNameForSearch = stationNameForSearch;
    }
}
