package com.korzinov.beans;

import com.korzinov.models.TrainModel;
import javax.inject.Named;
import java.util.List;

@Named(value = "trainBean")
public class TrainBean {

    private String trainName;
    private TrainModel train;
    private List<TrainModel> listTrains;

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public TrainModel getTrain() {
        return train;
    }

    public void setTrain(TrainModel train) {
        this.train = train;
    }

    public List<TrainModel> getListTrains() {
        return listTrains;
    }

    public void setListTrains(List<TrainModel> listtrains) {
        this.listTrains = listtrains;
    }
}
