package com.korzinov.dao;

import com.korzinov.entities.ScheduleEntity;
import com.korzinov.entities.StationEntity;

import java.util.Date;
import java.util.List;

public interface ScheduleDao {

    List<ScheduleEntity> findTrainsForUser(String depStation, String destStation/*, Date date*/);
}
