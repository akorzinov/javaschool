package com.korzinov.services;

import com.korzinov.entities.StationEntity;
import org.primefaces.event.RowEditEvent;

public interface StationService {

    void findByNameStation();

    void addStation();

    void updateStation(RowEditEvent event);

    void deleteStation(StationEntity st);

    int findIdByStationName(String stName);
}
