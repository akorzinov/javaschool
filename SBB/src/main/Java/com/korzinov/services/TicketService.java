package com.korzinov.services;

import com.korzinov.entities.TicketEntity;
import com.korzinov.entities.TicketTableModel;
import com.korzinov.entities.TrainEntity;
import com.korzinov.entities.UserEntity;

import java.util.Date;
import java.util.List;

public interface TicketService {

    List<TicketTableModel> listTickets();

    List<TicketTableModel> buyTickets(List<TicketEntity> tickets,  List<TicketTableModel> listTicket);

    UserEntity getUser();

    boolean checkSamePass(TrainEntity train, String firstName, String lastName, Date birthday);

    List<TicketEntity> listTicketsDb(List<TicketTableModel> list);
}
