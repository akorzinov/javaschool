package com.korzinov.controllers;

import com.korzinov.beans.RouteBean;
import com.korzinov.models.RouteModel;
import com.korzinov.models.TrainModel;
import com.korzinov.services.RouteService;
import com.korzinov.services.TrainService;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "routeController")
@SessionScoped
@Controller
public class RouteController implements Serializable{

    @Autowired
    private RouteService routeService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private RouteBean routeBean;

    public void findRoute() {
        routeBean.setListRoutes(routeService.findRoute(routeBean.getTrainName()));
        routeBean.setListTrain(routeService.findTrain(routeBean.getTrainName()));
    }

    public void addRoute() {
        routeService.addRoute(routeBean.getTrain(), routeBean.getRoute(), routeBean.getStationName());
        routeBean.setListTrain(routeService.findTrain(routeBean.getTrain().getTrainName()));
        routeBean.setListRoutes(routeService.findRoute(routeBean.getTrain().getTrainName()));
    }

    public void editRoute(RowEditEvent event) {
        routeBean.setListRoutes(routeService.updateRoute(routeBean.getListRoutes(), routeBean.getOldValue(), event));
    }

    public void editInit(RowEditEvent event) {
        RouteModel oldTicket = new RouteModel((RouteModel)event.getObject());
        routeBean.setOldValue(oldTicket);
    }

    public void deleteRoute(RouteModel rm) {
        routeService.deleteRoute(rm);
        routeBean.setListRoutes(routeService.findRoute(routeBean.getTrainName()));
    }

    public void addTrain() {
        trainService.addTrain(routeBean.getTrain());
    }

    public void editTrain(RowEditEvent event) {
        trainService.updateTrain(event);
    }

    public void deleteTrain(TrainModel train) {
        trainService.deleteTrain(train);
        routeBean.setListTrain(routeService.findTrain(routeBean.getTrainName()));
    }

    public RouteService getRouteService() {
        return routeService;
    }

    public void setRouteService(RouteService routeService) {
        this.routeService = routeService;
    }

    public TrainService getTrainService() {
        return trainService;
    }

    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    public RouteBean getRouteBean() {
        return routeBean;
    }

    public void setRouteBean(RouteBean routeBean) {
        this.routeBean = routeBean;
    }
}
