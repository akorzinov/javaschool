package com.korzinov.services;

import com.korzinov.models.RouteModel;
import org.primefaces.event.RowEditEvent;


public interface ScheduleService {

    void findTrainsForUser();

    void findScheduleByStation();

    void findRoute();

    void addRoute();

    void updateRoute(RowEditEvent event);

    void deleteRoute(RouteModel rm);
}
