package com.korzinov.dao;

import com.korzinov.entities.FindTrain;
import com.korzinov.entities.ScheduleEntity;
import com.korzinov.entities.StationEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface ScheduleDao {

    List<FindTrain> findTrainsForUser(String depStation, String destStation, Date date);
}
