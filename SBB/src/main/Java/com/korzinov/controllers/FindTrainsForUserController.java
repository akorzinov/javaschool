package com.korzinov.controllers;

import com.korzinov.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "FindTrainsForUserController")
@SessionScoped
@Controller
public class FindTrainsForUserController implements Serializable{

    private String depStation;
    private String destStation;

    @Autowired
    private ScheduleService scheduleService;

    public String findTrain() {
        scheduleService.findTrainsForUser(getDepStation(),getDestStation());
        return null;
    }

    public String getDepStation() {
        return depStation;
    }

    public void setDepStation(String depStation) {
        this.depStation = depStation;
    }

    public String getDestStation() {
        return destStation;
    }

    public void setDestStation(String destStation) {
        this.destStation = destStation;
    }

    public ScheduleService getScheduleService() {
        return scheduleService;
    }

    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
}
