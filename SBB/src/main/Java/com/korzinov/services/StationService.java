package com.korzinov.services;

import com.korzinov.entities.StationEntity;

import java.util.List;

public interface StationService {

    List<StationEntity> findByNameStation(String nameStation);

    void addStation(StationEntity st);

    void updateStation(StationEntity st);

    void deleteStation(StationEntity st);
}
