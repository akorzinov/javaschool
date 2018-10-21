package com.korzinov.services;

import com.korzinov.entities.ScheduleEntity;

import java.util.List;

public interface ScheduleService {

    List<ScheduleEntity> findTrainsForUser(String depStation, String destStation/*, Date date*/);
}
