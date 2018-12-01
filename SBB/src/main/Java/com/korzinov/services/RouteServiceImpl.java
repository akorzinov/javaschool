package com.korzinov.services;

import com.korzinov.dao.RouteDao;
import com.korzinov.dao.StationDao;
import com.korzinov.dao.TrainDao;
import com.korzinov.entities.RouteEntity;
import com.korzinov.entities.StationEntity;
import com.korzinov.entities.TrainEntity;
import com.korzinov.models.RouteModel;
import com.korzinov.models.TrainModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RouteServiceImpl implements RouteService {

    static final Logger logger = LogManager.getLogger(RouteServiceImpl.class);

    @Autowired
    private RouteDao routeDao;

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private StationDao stationDao;

    @Override
    public List<RouteModel> findRoute(String trainName) {
        return routeDao.findRoute(trainName);
    }

    @Override
    public List<TrainModel> findTrain(String trainName) {
        List<TrainEntity> listTrain = trainDao.findByNameTrain(trainName);
        List<TrainModel> trains = new ArrayList<>();
        for (int i = 0; i < listTrain.size(); i++) {
            trains.add(i, new TrainModel(listTrain.get(i)));
        }
        return trains;
    }

    @Override
    public void addRoute(TrainModel train, RouteModel route, String stationName) {
        trainDao.addTrain(convertTrainModel(train));
        StationEntity st = stationDao.findByNameStationUnique(stationName);
        if (st != null) {
            routeDao.addRoute(convertRouteModel(route, train.getTrainName(), st));
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Route Successfully added"));
        } else {
            logger.error("Entered stationName " + stationName + " doesn't exist, need enter exist stationName");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Station name " + stationName + " doesn't exist, need enter existed station name"));
        }
    }

    @Override
    public void updateRoute(RowEditEvent event) {
        RouteModel rm = (RouteModel)event.getObject();
        RouteEntity rt = new RouteEntity();
        rt.setRouteId(rm.getRouteId());
        rt.setTrainByTrainId(trainDao.findByNameTrainUnique(rm.getTrainName()));
        StationEntity st = stationDao.findByNameStationUnique(rm.getStationName());
        if (st != null) {
            rt.setStationByStationId(st);
        } else {
            logger.error("Entered stationName " + rm.getStationName() + " doesn't exist, need enter exist stationName");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Station name " + rm.getStationName() + " doesn't exist, need enter existed station name"));
            return;
        }
        rt.setOrderStation(rm.getOrderStation());
        routeDao.updateRoute(rt);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Route Updated"));
    }

    @Override
    public void deleteRoute(RouteModel rm) {
        RouteEntity rt = new RouteEntity();
        rt.setRouteId(rm.getRouteId());
        rt.setTrainByTrainId(trainDao.findByNameTrainUnique(rm.getTrainName()));
        rt.setStationByStationId(stationDao.findByNameStationUnique(rm.getStationName()));
        rt.setOrderStation(rm.getOrderStation());
        routeDao.deleteRoute(rt);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Route Deleted"));
    }

    @Override
    public TrainEntity convertTrainModel(TrainModel train) {
        TrainEntity newTrain = new TrainEntity();
        newTrain.setTrainName(train.getTrainName());
        if (train.getQuantitySeats() != null) {
            newTrain.setQuantitySeats(train.getQuantitySeats());
        }
        return newTrain;
    }

    @Override
    public RouteEntity convertRouteModel(RouteModel route, String trainName, StationEntity station) {
        RouteEntity newRoute = new RouteEntity();
        newRoute.setTrainByTrainId(trainDao.findByNameTrainUnique(trainName));
        newRoute.setStationByStationId(station);
        newRoute.setOrderStation(route.getOrderStation());
        return newRoute;
    }

    @Override
    public List<RouteModel> loadSchedules(String trainName) {
        return routeDao.findRoute(trainName);
    }

    public RouteDao getRouteDao() {
        return routeDao;
    }

    public void setRouteDao(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    public TrainDao getTrainDao() {
        return trainDao;
    }

    public void setTrainDao(TrainDao trainDao) {
        this.trainDao = trainDao;
    }

    public StationDao getStationDao() {
        return stationDao;
    }

    public void setStationDao(StationDao stationDao) {
        this.stationDao = stationDao;
    }
}
