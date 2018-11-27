package com.korzinov.controllers;

import com.korzinov.beans.TrainBean;
import com.korzinov.models.FindTrain;
import com.korzinov.services.TrainDetailsService;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "trainDetailsController")
@SessionScoped
@Controller
public class TrainDetailsController implements Serializable{

    @Autowired
    private TrainDetailsService trainDetailsService;

    @Autowired
    private TrainBean trainBean;

    private List<FindTrain> listTrains;

    public void findTrainDetails() {
        if (trainBean.getTrainName().equals("")) {
            init();
        } else {
            listTrains = trainDetailsService.findTrainDetails(trainBean.getTrainName());
        }
        trainBean.setTrainName("");
        trainBean.setRenderTickets(false);
        trainBean.setRenderTrains(true);
        trainBean.setRenderBackButton(false);
    }

//    @PostConstruct
    public void init() {
        listTrains = trainDetailsService.findTrainDetailsAll();
    }

    public void onDblClickRowSelect(SelectEvent event) {
        FindTrain train = (FindTrain)event.getObject();
        trainBean.setListPassengers(trainDetailsService.findPassengersByTrain(train.getTrainName()));
        trainBean.setRenderTickets(true);
        trainBean.setRenderTrains(false);
        trainBean.setRenderBackButton(true);
    }

    public void back() {
        trainBean.setRenderTickets(false);
        trainBean.setRenderTrains(true);
        trainBean.setRenderBackButton(false);
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

    public List<FindTrain> getListTrains() {
        return listTrains;
    }

    public void setListTrains(List<FindTrain> listTrains) {
        this.listTrains = listTrains;
    }
}

