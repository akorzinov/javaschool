package com.korzinov.services;

import com.korzinov.entities.StationEntity;
import com.korzinov.models.StationModel;
import org.primefaces.event.RowEditEvent;

import java.util.List;

public interface StationService {

    List<StationModel> findByNameStation(String stationName);

    void addStation(StationModel station);

    void updateStation(RowEditEvent event);

    void deleteStation(StationModel station);

    int findIdByStationName(String stName);

}
