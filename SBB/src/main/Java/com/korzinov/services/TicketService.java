package com.korzinov.services;

import com.korzinov.entities.TicketEntity;
import com.korzinov.entities.TicketTableModel;
import com.korzinov.entities.TrainEntity;
import com.korzinov.entities.UserEntity;
import org.primefaces.event.RowEditEvent;

import java.util.Date;
import java.util.List;

public interface TicketService {

    List<TicketTableModel> listTickets();

    void buyTickets();

    UserEntity getUser();

    boolean checkSamePassenger(TrainEntity train, String firstName, String lastName, Date birthday);

    boolean checkSamePassList(List<TicketTableModel> listTicket, String firstName, String lastName, Date birthday);

    List<TicketEntity> listTicketsDb(List<TicketTableModel> list);

    void addPassenger();

    void editTicket(RowEditEvent event);

    void editInit(RowEditEvent event);

    String deleteTicket(TicketTableModel ticket);
}
