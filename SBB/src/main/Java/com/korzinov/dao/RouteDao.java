package com.korzinov.dao;

import com.korzinov.entities.RouteEntity;
import com.korzinov.models.RouteModel;

import java.util.List;

public interface RouteDao {

    Integer findQuantityRoute(String trainName);

    List<RouteModel> findRoute(String trainName);

    void addRoute(RouteEntity route);

    void updateRoute(RouteEntity route);

    void deleteRoute(RouteEntity route);
}
