package com.korzinov.controllers;

import com.korzinov.beans.TrainBean;
import com.korzinov.models.FindTrain;
import com.korzinov.services.TrainDetailsService;
import org.primefaces.event.SelectEvent;
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
        trainBean.setRenderTickets(false);
        trainBean.setRenderTrains(true);
    }

    public void findTrainsDetailsAll() {
        trainBean.setListTrains(trainDetailsService.findTrainDetailsAll());
        trainBean.setRenderTickets(false);
        trainBean.setRenderTrains(true);
    }

    public void onDblClickRowSelect(SelectEvent event) {
        FindTrain train = (FindTrain)event.getObject();
        trainBean.setListPassengers(trainDetailsService.findPassengersByTrain(train.getTrainName()));
        trainBean.setRenderTickets(true);
        trainBean.setRenderTrains(false);
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

