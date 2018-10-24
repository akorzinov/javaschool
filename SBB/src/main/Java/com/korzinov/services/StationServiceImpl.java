package com.korzinov.services;

import com.korzinov.dao.StationDao;
import com.korzinov.entities.StationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class StationServiceImpl implements StationService {

    @Autowired
    private StationDao stationDao;

    @Override
    public List<StationEntity> findByNameStation(String nameStation) {
        return stationDao.findByNameStation(nameStation);
    }

    @Override
    public void addStation(StationEntity st) {
        stationDao.addStation(st);

    }

    @Override
    public void updateStation(StationEntity st) {
        stationDao.updateStation(st);
    }

    @Override
    public void deleteStation(StationEntity st) {
        stationDao.deleteStation(st);
    }

    public StationDao getStationDao() {
        return stationDao;
    }

    public void setStationDao(StationDao stationDao) {
        this.stationDao = stationDao;
    }
}
