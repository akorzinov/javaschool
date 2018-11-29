package com.korzinov.services;

import com.korzinov.entities.RouteEntity;
import com.korzinov.entities.StationEntity;
import com.korzinov.entities.TrainEntity;
import com.korzinov.models.RouteModel;
import com.korzinov.models.TrainModel;
import org.primefaces.event.RowEditEvent;

import java.util.List;

public interface RouteService {

    List<RouteModel> findRoute(String trainName);

    List<TrainModel> findTrain(String trainName);

    void addRoute(TrainModel train, RouteModel route, String stationName);

    void updateRoute(RowEditEvent event);

    void deleteRoute(RouteModel rm);

    TrainEntity convertTrainModel(TrainModel train);

    RouteEntity convertRouteModel(RouteModel route, String trainName, StationEntity station);
}
