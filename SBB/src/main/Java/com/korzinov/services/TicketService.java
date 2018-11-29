package com.korzinov.services;

import com.korzinov.entities.ScheduleEntity;
import com.korzinov.entities.TicketEntity;
import com.korzinov.models.FindTrain;
import com.korzinov.models.TicketTableModel;
import com.korzinov.entities.TrainEntity;
import com.korzinov.entities.UserEntity;
import java.util.Date;
import java.util.List;

public interface TicketService {

    List<TicketTableModel> listTickets();

    List<TicketTableModel> buyTickets(List<TicketTableModel> listTicket);

    UserEntity getUser();

    boolean checkSamePassenger(TrainEntity train, String firstName, String lastName, Date birthday, Date depTime);

    boolean checkSamePassList(List<TicketTableModel> listTicket, String firstName, String lastName, Date birthday);

    List<TicketEntity> listTicketsDb(List<TicketTableModel> list);

    List<TicketTableModel> addPassenger(List<TicketTableModel> listTicket, FindTrain findTrain, TicketTableModel ticketForTable);

    List<TicketTableModel> editTicket(List<TicketTableModel> listTicket, TicketTableModel ticket, TicketTableModel oldValue);

    List<TicketTableModel> deleteTicket(List<TicketTableModel> listTicket, TicketTableModel ticket);

    List<ScheduleEntity> findUniqueScheduleList(String trainName, Date depTime, List<ScheduleEntity> listSchedule);
}