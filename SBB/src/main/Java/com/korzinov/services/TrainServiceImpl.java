package com.korzinov.services;

import com.korzinov.dao.TrainDao;
import com.korzinov.entities.TrainEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainDao trainDao;

    @Override
    public List<TrainEntity> findByNameTrain(String train) {
        return trainDao.findByNameTrain(train);
    }

    @Override
    public void addTrain(TrainEntity tr) {
        trainDao.addTrain(tr);
    }

    @Override
    public void updateTrain(TrainEntity tr) {
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
