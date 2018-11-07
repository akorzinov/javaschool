package com.korzinov.controllers;

import com.korzinov.entities.StationEntity;
import com.korzinov.services.StationService;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "stationController")
@SessionScoped
@Controller
public class StationController implements Serializable{

    @Autowired
    private StationService stationService;

    public String FindStation() {
        stationService.findByNameStation();
        return null;
    }

    public String addStation() {
        stationService.addStation();
        return null;
    }

    public void editStation(RowEditEvent event) {
        stationService.updateStation(event);
    }

    public String deleteStation(StationEntity st) {
        stationService.deleteStation(st);
        return null;
    }

    public StationService getStationService() {
        return stationService;
    }

    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

}
