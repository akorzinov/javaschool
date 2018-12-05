package com.korzinov.beans;

import com.korzinov.models.RouteModel;
import com.korzinov.models.TrainModel;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import java.util.List;

@Named(value = "routeBean")
@Scope("session")
public class RouteBean {

    private String trainName;
    private String stationName;
    private TrainModel train = new TrainModel();
    private RouteModel route = new RouteModel();
    private List<TrainModel> listTrain;
    private List<RouteModel> listRoutes;
    private RouteModel oldValue = new RouteModel();

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

    public RouteModel getRoute() {
        return route;
    }

    public void setRoute(RouteModel route) {
        this.route = route;
    }

    public List<TrainModel> getListTrain() {
        return listTrain;
    }

    public void setListTrain(List<TrainModel> listTrain) {
        this.listTrain = listTrain;
    }

    public List<RouteModel> getListRoutes() {
        return listRoutes;
    }

    public void setListRoutes(List<RouteModel> listRoutes) {
        this.listRoutes = listRoutes;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public RouteModel getOldValue() {
        return oldValue;
    }

    public void setOldValue(RouteModel oldValue) {
        this.oldValue = oldValue;
    }
}
