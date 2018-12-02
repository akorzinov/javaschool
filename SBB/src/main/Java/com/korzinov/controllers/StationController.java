package com.korzinov.controllers;

import com.korzinov.beans.StationBean;
import com.korzinov.entities.StationEntity;
import com.korzinov.models.StationModel;
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

    @Autowired
    private StationBean stationBean;

    public void FindStation() {
        stationBean.setListStation(stationService.findByNameStation(stationBean.getStationNameForSearch()));
    }

    public void addStation() {
        stationService.addStation(stationBean.getStation());
        stationBean.setListStation(stationService.findByNameStation(stationBean.getStation().getStationName()));
    }

    public void editStation(RowEditEvent event) {
        stationService.updateStation(event);
    }

    public void deleteStation(StationModel station) {
        stationService.deleteStation(station);
        stationBean.setListStation(stationService.findByNameStation(stationBean.getStationNameForSearch())); /*может будет работать и без этого*/
    }

    public StationService getStationService() {
        return stationService;
    }

    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    public StationBean getStationBean() {
        return stationBean;
    }

    public void setStationBean(StationBean stationBean) {
        this.stationBean = stationBean;
    }
}
