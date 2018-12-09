package com.korzinov.services;

import com.korzinov.dao.RouteDao;
import com.korzinov.dao.TrainDao;
import com.korzinov.entities.TrainEntity;
import com.korzinov.models.TrainModel;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Service
@Transactional
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private RouteDao routeDao;

    @Override
    public void addTrain(TrainModel train) {
        TrainEntity newtrain = new TrainEntity();
        newtrain.setTrainName(train.getTrainName());
        newtrain.setQuantitySeats(train.getQuantitySeats());
        trainDao.addTrain(newtrain);
    }

    @Override
    public void updateTrain(RowEditEvent event) {
        TrainModel train = (TrainModel)event.getObject();
        TrainEntity tr = new TrainEntity();
        tr.setTrainName(train.getTrainName());
        tr.setQuantitySeats(train.getQuantitySeats());
        tr.setTrainId(train.getTrainId());
        trainDao.updateTrain(tr);
    }

    @Override
    public void deleteTrain(TrainModel train) {
        if (!routeDao.findRouteByTrainName(train.getTrainName()).isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Train " + train.getTrainName() + " can't be deleted, it has related routes"));
            return;
        }
        TrainEntity tr = new TrainEntity();
        tr.setTrainName(train.getTrainName());
        tr.setQuantitySeats(train.getQuantitySeats());
        tr.setTrainId(train.getTrainId());
        trainDao.deleteTrain(tr);
    }

    public TrainDao getTrainDao() {
        return trainDao;
    }

    public void setTrainDao(TrainDao trainDao) {
        this.trainDao = trainDao;
    }

    public RouteDao getRouteDao() {
        return routeDao;
    }

    public void setRouteDao(RouteDao routeDao) {
        this.routeDao = routeDao;
    }
}
