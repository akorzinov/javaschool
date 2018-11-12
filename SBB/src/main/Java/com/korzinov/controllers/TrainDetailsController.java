package com.korzinov.controllers;

import com.korzinov.beans.TrainBean;
import com.korzinov.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "trainDetailsController")
@SessionScoped
@Controller
public class TrainDetailsController implements Serializable{

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TrainBean trainBean;



    public ScheduleService getScheduleService() {
        return scheduleService;
    }

    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    public TrainBean getTrainBean() {
        return trainBean;
    }

    public void setTrainBean(TrainBean trainBean) {
        this.trainBean = trainBean;
    }
}
