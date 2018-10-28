package com.korzinov.services;

import com.korzinov.dao.ScheduleDao;
import com.korzinov.entities.FindTrain;
import com.korzinov.entities.RouteModel;
import com.korzinov.entities.ScheduleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private ScheduleDao scheduleDao;

    @Override
    public List<FindTrain> findTrainsForUser(String depStation, String destStation, Date date) {
        return scheduleDao.findTrainsForUser(depStation,destStation,date);
    }

    @Override
    public List<FindTrain> findScheduleByStation(String station) {
        return scheduleDao.findScheduleByStation(station);
    }

    @Override
    public List<RouteModel> findRoute(String trainName) {
        return scheduleDao.findRoute(trainName);
    }

    @Override
    public void addRoute(ScheduleEntity schedule) {
        scheduleDao.addRoute(schedule);
    }

    @Override
    public void updateRoute(ScheduleEntity schedule) {
        scheduleDao.updateRoute(schedule);
    }

    @Override
    public void deleteRoute(ScheduleEntity schedule) {
        scheduleDao.deleteRoute(schedule);
    }

    public ScheduleDao getScheduleDao() {
        return scheduleDao;
    }

    public void setScheduleDao(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }
}
