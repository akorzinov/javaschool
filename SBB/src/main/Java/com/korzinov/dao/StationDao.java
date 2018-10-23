package com.korzinov.dao;

import com.korzinov.entities.StationEntity;

public interface StationDao {

    StationEntity findByNameStation(String nameStation);
}
