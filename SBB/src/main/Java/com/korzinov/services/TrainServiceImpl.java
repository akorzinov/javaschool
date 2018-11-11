package com.korzinov.services;

import com.korzinov.dao.TrainDao;
import com.korzinov.entities.TrainEntity;
import com.korzinov.models.TrainModel;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainDao trainDao;

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
        trainDao.updateTrain(tr);
    }

    @Override
    public void deleteTrain(TrainEntity tr) {
        trainDao.deleteTrain(tr);
    }

    public TrainDao getTrainDao() {
        return trainDao;
    }

    public void setTrainDao(TrainDao trainDao) {
        this.trainDao = trainDao;
    }

}
