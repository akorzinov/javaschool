package com.korzinov.services;

import com.korzinov.beans.TrainBean;
import com.korzinov.dao.TrainDao;
import com.korzinov.entities.TrainEntity;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

@Service
@Transactional
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainDao trainDao;

    @Autowired
    private TrainBean trainBean;

    @Override
    public void findByNameTrain() {
        trainBean.setListTrains(trainDao.findByNameTrain(trainBean.getTrainName()));
    }

    @Override
    public void addTrain() {
        trainDao.addTrain(trainBean.getTrain());
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Train " + trainBean.getTrain().getTrainName() + " Successfully added"));
    }

    @Override
    public void updateTrain(RowEditEvent event) {
        TrainEntity tr = (TrainEntity)event.getObject();
        trainDao.updateTrain(tr);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Train " + tr.getTrainName() + " Updated"));
    }

    @Override
    public void deleteTrain(TrainEntity tr) {
        trainDao.deleteTrain(tr);
        trainBean.setListTrains(trainDao.findByNameTrain(trainBean.getTrainName()));
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Train " + tr.getTrainName() + " Deleted"));
    }

    public TrainDao getTrainDao() {
        return trainDao;
    }

    public void setTrainDao(TrainDao trainDao) {
        this.trainDao = trainDao;
    }

    public TrainBean getTrainBean() {
        return trainBean;
    }

    public void setTrainBean(TrainBean trainBean) {
        this.trainBean = trainBean;
    }
}
