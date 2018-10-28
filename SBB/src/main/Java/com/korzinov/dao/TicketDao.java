package com.korzinov.dao;

import com.korzinov.entities.TicketEntity;
import com.korzinov.entities.UserEntity;

import java.util.List;

public interface TicketDao {

    List<TicketEntity> listTickets(UserEntity user);

    void buyTickets(List<TicketEntity> tickets);
}
