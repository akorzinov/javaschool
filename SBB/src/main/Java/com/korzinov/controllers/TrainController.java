package com.korzinov.controllers;

import com.korzinov.entities.TrainEntity;
import com.korzinov.services.TrainService;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "trainController")
@SessionScoped
@Controller
public class TrainController implements Serializable{

    private String trainName;
    private TrainEntity train;
    private List<TrainEntity> listTrains;

    @Autowired
    private TrainService trainService;

    public String FindTrain() {
        listTrains = trainService.findByNameTrain(trainName);
        return null;
    }

    public String addStation() {
        trainService.addTrain(train);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Train " + train.getTrainName() + " Successfully added"));
        return null;
    }

    public void editTrain(RowEditEvent event) {
        TrainEntity tr = (TrainEntity)event.getObject();
        trainService.updateTrain(tr);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Train " + train.getTrainName() + " Updated"));
    }

    public String deleteTrain(TrainEntity tr) {
        trainService.deleteTrain(tr);
        listTrains = trainService.findByNameTrain(trainName);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Train " + train.getTrainName() + " Deleted"));
        return null;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public TrainEntity getTrain() {
        return train;
    }

    public void setTrain(TrainEntity train) {
        this.train = train;
    }

    public List<TrainEntity> getListTrains() {
        return listTrains;
    }

    public void setListTrains(List<TrainEntity> listTrains) {
        this.listTrains = listTrains;
    }

    public TrainService getTrainService() {
        return trainService;
    }

    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }
}
