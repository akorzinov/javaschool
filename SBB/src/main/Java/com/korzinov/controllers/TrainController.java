package com.korzinov.controllers;

import com.korzinov.entities.TrainEntity;
import com.korzinov.services.TrainService;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "trainController")
@SessionScoped
@Controller
public class TrainController implements Serializable{

    @Autowired
    private TrainService trainService;

    public String FindTrain() {
        trainService.findByNameTrain();
        return null;
    }

    public String addStation() {
        trainService.addTrain();
        return null;
    }

    public void editTrain(RowEditEvent event) {
        trainService.updateTrain(event);
    }

    public String deleteTrain(TrainEntity tr) {
        trainService.deleteTrain(tr);
        return null;
    }

    public TrainService getTrainService() {
        return trainService;
    }

    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }
}
