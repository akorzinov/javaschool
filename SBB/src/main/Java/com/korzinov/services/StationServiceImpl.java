package com.korzinov.services;

import com.korzinov.beans.StationBean;
import com.korzinov.dao.StationDao;
import com.korzinov.entities.StationEntity;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Service
@Transactional
public class StationServiceImpl implements StationService {

    @Autowired
    private StationDao stationDao;

    @Autowired
    private StationBean stationBean;

    @Override
    public void findByNameStation() {
        stationBean.setListStation(stationDao.findByNameStation(stationBean.getStationNameForSearch()));
    }

    @Override
    public void addStation() {
        stationDao.addStation(stationBean.getStation());
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Station " + stationBean.getStation().getStationName() + " Successfully added"));
    }

    @Override
    public void updateStation(RowEditEvent event) {
        StationEntity st = (StationEntity)event.getObject();
        stationDao.updateStation(st);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Station " + st.getStationName() + " Updated"));
    }

    @Override
    public void deleteStation(StationEntity st) {
        stationDao.deleteStation(st);
        stationBean.setListStation(stationDao.findByNameStation(stationBean.getStationNameForSearch()));
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Station " + st.getStationName() + " Deleted"));
    }

    @Override
    public int findIdByStationName(String stName) {
        return stationDao.findIdByStationName(stName);
    }

    public StationDao getStationDao() {
        return stationDao;
    }

    public void setStationDao(StationDao stationDao) {
        this.stationDao = stationDao;
    }

    public StationBean getStationBean() {
        return stationBean;
    }

    public void setStationBean(StationBean stationBean) {
        this.stationBean = stationBean;
    }
}
