package com.korzinov.models;

import com.korzinov.entities.TrainEntity;

public class TrainModel {
    private int trainId;
    private String trainName;
    private Integer quantitySeats;

    public TrainModel() {
    }

    public TrainModel(int trainId, String trainName, Integer quantitySeats) {
        this.trainId = trainId;
        this.trainName = trainName;
        this.quantitySeats = quantitySeats;
    }

    public TrainModel(TrainEntity train) {
        this.trainId = train.getTrainId();
        this.trainName = train.getTrainName();
        this.quantitySeats = train.getQuantitySeats();
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public Integer getQuantitySeats() {
        return quantitySeats;
    }

    public void setQuantitySeats(Integer quantitySeats) {
        this.quantitySeats = quantitySeats;
    }
}
