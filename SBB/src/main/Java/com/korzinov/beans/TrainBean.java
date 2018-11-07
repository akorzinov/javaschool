package com.korzinov.beans;

import com.korzinov.entities.TrainEntity;

import javax.inject.Named;
import java.util.List;

@Named(value = "trainBean")
public class TrainBean {

    private String trainName;
    private TrainEntity train;
    private List<TrainEntity> listTrains;

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
}
