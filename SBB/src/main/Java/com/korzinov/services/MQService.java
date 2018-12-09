package com.korzinov.services;

import com.korzinov.models.RouteModel;
import org.primefaces.event.RowEditEvent;

import java.util.List;

public interface MQService {

    void send(RowEditEvent event, List<RouteModel> listSchedules);

    void sendCanceled(RouteModel route, List<RouteModel> listSchedules);
}
