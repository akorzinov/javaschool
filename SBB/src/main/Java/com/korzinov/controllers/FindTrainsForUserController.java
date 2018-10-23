package com.korzinov.controllers;

import com.korzinov.entities.FindTrain;
import com.korzinov.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named(value = "findTrainsForUserController")
@SessionScoped
//@Controller
//@ViewScoped
public class FindTrainsForUserController implements Serializable{

    private String depStation;
    private String destStation;
    private Date date;
    private List<FindTrain> listFoundTrains = new ArrayList<>();
    private Boolean renderTable;
    private String station;
    private List<FindTrain> listSchedule;

    public FindTrainsForUserController() {}

    @Autowired
    private ScheduleService scheduleService;

    public void findTrain() {
        renderTable = true;
        listFoundTrains = scheduleService.findTrainsForUser(getDepStation(),getDestStation(),getDate());
    }

    public void ScheduleByStation() {
        listSchedule = scheduleService.findScheduleByStation(getStation());

    }

    public String buyTicket() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        long d = date.getTime();
        date.setTime(d - 10800000L);
        this.date = date;
    }

    public List<FindTrain> getListFoundTrains() {
        return listFoundTrains;
    }

    public void setListFoundTrains(List<FindTrain> listFoundTrains) {
        this.listFoundTrains = listFoundTrains;
    }

    public ScheduleService getScheduleService() {
        return scheduleService;
    }

    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    public Boolean getRenderTable() {
        return renderTable;
    }

    public void setRenderTable(Boolean renderTable) {
        this.renderTable = renderTable;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public List<FindTrain> getListSchedule() {
        return listSchedule;
    }

    public void setListSchedule(List<FindTrain> listSchedule) {
        this.listSchedule = listSchedule;
    }
}
