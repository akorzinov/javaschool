package com.korzinov.services;

import com.korzinov.entities.TicketEntity;
import com.korzinov.entities.UserEntity;

import java.util.List;

public interface TicketService {

    List<TicketEntity> listTickets();

    void buyTickets(List<TicketEntity> tickets);

    UserEntity getUser();
}
