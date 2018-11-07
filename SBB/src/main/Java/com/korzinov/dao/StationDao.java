package com.korzinov.dao;

import com.korzinov.entities.StationEntity;

import java.util.List;

public interface StationDao {

    List<StationEntity> findByNameStation(String nameStation);

    int findIdByStationName(String stName);

    void addStation(StationEntity st);

    void updateStation(StationEntity st);

    void deleteStation(StationEntity st);

    String findStationNameById(int id);

}
