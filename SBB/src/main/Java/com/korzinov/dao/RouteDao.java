package com.korzinov.dao;

import com.korzinov.entities.RouteEntity;
import com.korzinov.entities.TrainEntity;
import com.korzinov.models.RouteModel;

import java.util.List;

public interface RouteDao {

    Integer findQuantityRoute(String trainName);

    List<RouteModel> findRoute(String trainName);

    void addRoute(RouteEntity route);

    void updateRoute(RouteEntity route);

    void deleteRoute(RouteEntity route);

    List<RouteEntity> findRouteByListId(List<Integer> listRoutesId);

    RouteEntity findRouteById(int routeId);

    List<RouteEntity> findRouteByStationName(String stationName);

    RouteEntity findRouteByOrderAndTrainName(String trainName, int order);

    List<RouteEntity> findRouteByTrainName(String trainName);

    RouteEntity findRouteByTrainNameAndStationName(String trainName, String stationName);
}
