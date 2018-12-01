package com.korzinov.dao;

import com.korzinov.entities.TicketEntity;
import com.korzinov.entities.TrainEntity;
import com.korzinov.entities.UserEntity;
import com.korzinov.models.TicketTableModel;

import java.util.Date;
import java.util.List;

public interface TicketDao {

    List<TicketEntity> listTickets(UserEntity user);

    void buyTickets(List<TicketEntity> tickets);

    boolean checkSamePass(List<Integer> listSchedulesId, String firstName, String lastName, Date birthday);

    List<TicketTableModel> findPassengersByTrain(String trainName);
}
