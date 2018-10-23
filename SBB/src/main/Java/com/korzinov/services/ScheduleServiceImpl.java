package com.korzinov.services;

import com.korzinov.dao.ScheduleDao;
import com.korzinov.entities.FindTrain;
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

    public ScheduleDao getScheduleDao() {
        return scheduleDao;
    }

    public void setScheduleDao(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }
}
