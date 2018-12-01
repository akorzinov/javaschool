package com.korzinov.services;

import org.primefaces.event.RowEditEvent;

public interface MQService {

    void send(RowEditEvent event);
}
