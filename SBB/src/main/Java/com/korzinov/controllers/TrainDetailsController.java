package com.korzinov.controllers;

import com.korzinov.beans.TrainBean;
import com.korzinov.services.TrainDetailsService;
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
    private TrainDetailsService trainDetailsService;

    @Autowired
    private TrainBean trainBean;

    public void findTrainDetails() {
        trainBean.setListTrains(trainDetailsService.findTrainDetails(trainBean.getTrainName()));
    }

    public void findTrainsDetailsAll() {
        trainBean.setListTrains(trainDetailsService.findTrainDetailsAll());
    }

    public TrainDetailsService getTrainDetailsService() {
        return trainDetailsService;
    }

    public void setTrainDetailsService(TrainDetailsService trainDetailsService) {
        this.trainDetailsService = trainDetailsService;
    }

    public TrainBean getTrainBean() {
        return trainBean;
    }

    public void setTrainBean(TrainBean trainBean) {
        this.trainBean = trainBean;
    }
}

