package com.korzinov.services;

import com.korzinov.dao.ScheduleDao;
import com.korzinov.entities.ScheduleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private ScheduleDao scheduleDao;

    @Override
    public List<ScheduleEntity> findTrainsForUser(String depStation, String destStation/*, Date date*/) {
        return scheduleDao.findTrainsForUser(depStation,destStation);
    }

    public ScheduleDao getScheduleDao() {
        return scheduleDao;
    }

    public void setScheduleDao(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }
}
