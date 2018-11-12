package com.korzinov.beans;

import com.korzinov.models.FindTrain;
import javax.inject.Named;
import java.util.List;

@Named(value = "trainBean")
public class TrainBean {

    private String trainName;
    private List<FindTrain> listTrains;

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public List<FindTrain> getListTrains() {
        return listTrains;
    }

    public void setListTrains(List<FindTrain> listTrains) {
        this.listTrains = listTrains;
    }
}
