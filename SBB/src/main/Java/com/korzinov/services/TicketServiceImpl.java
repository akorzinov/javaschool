package com.korzinov.services;

import com.korzinov.dao.*;
import com.korzinov.entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    static final Logger logger = LogManager.getLogger(TicketServiceImpl.class);

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private StationDao stationDao;

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private TrainDao trainDao;

    @Override
    public UserEntity getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        UserEntity user = userDao.findByUserName(userName);
        return user;
    }

    @Override
    public boolean checkSamePass(TrainEntity train, String firstName, String lastName, Date birthday) {
        return ticketDao.checkSamePass(train, firstName, lastName, birthday);
    }

    @Override
    public List<TicketTableModel> listTickets() {
        List<TicketEntity> listTickets = ticketDao.listTickets(getUser());
        List<TicketTableModel> listBoughtTickets = new ArrayList<>();
        for (int i = 0; i < listTickets.size() ; i++) {
            TicketTableModel ticket = new TicketTableModel();
            ticket.setTrainName(listTickets.get(i).getTrainByTrainId().getTrainName());
            ticket.setDepartureStationName(stationDao.findStationNameById(listTickets.get(i).getDepartureStationId()));
            ticket.setDestinationStationName(stationDao.findStationNameById(listTickets.get(i).getDestinationStationId()));
            ticket.setDepartureTime(listTickets.get(i).getDepartureTime());
            ticket.setArrivalTime(listTickets.get(i).getArrivalTime());
            ticket.setFirstName(listTickets.get(i).getFirstName());
            ticket.setLastName(listTickets.get(i).getLastName());
            ticket.setBirthday(listTickets.get(i).getBirthday());
            listBoughtTickets.add(ticket);
        }
        return listBoughtTickets;
    }

    @Override
    public List<TicketTableModel> buyTickets(List<TicketEntity> tickets, List<TicketTableModel> listTicket) {

        TrainEntity train = new TrainEntity();
        int freeSeats = 0;
        if (!tickets.isEmpty()) {
            train = tickets.get(0).getTrainByTrainId();
        }
        List<ScheduleEntity> listSchedule = scheduleDao.findScheduleByTrain(train);
        if (!listSchedule.isEmpty()) {
            freeSeats = listSchedule.get(0).getFreeSeats();
        }

        if ((freeSeats - tickets.size()) >= 0) {
            for (ScheduleEntity sc : listSchedule) {
                sc.setFreeSeats(sc.getFreeSeats() - tickets.size());
            }
            scheduleDao.updateFreeSeats(listSchedule);
            ticketDao.buyTickets(tickets);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Tickets has been booked successfully"));
            listTicket.clear();
            return listTicket;
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Sorry, there are only " + freeSeats + " free seats and you want to book " +
                     tickets.size() + " tickets, you can find other train"));
            return listTicket;
        }

    }

    @Override
    public List<TicketEntity> listTicketsDb(List<TicketTableModel> list) {
        List<TicketEntity> newListTicket = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            TicketEntity ticket = new TicketEntity();
            ticket.setUserByUserId(getUser());
            ticket.setTrainByTrainId(trainDao.findByNameTrain(list.get(i).getTrainName()).get(0));
            ticket.setDepartureStationId(stationDao.findByNameStation(list.get(i).getDepartureStationName()).get(0).getStationId());
            ticket.setDestinationStationId(stationDao.findByNameStation(list.get(i).getDestinationStationName()).get(0).getStationId());
            ticket.setDepartureTime(list.get(i).getDepartureTime());
            ticket.setArrivalTime(list.get(i).getArrivalTime());
            ticket.setFirstName(list.get(i).getFirstName());
            ticket.setLastName(list.get(i).getLastName());
            ticket.setBirthday(list.get(i).getBirthday());
            newListTicket.add(ticket);
        }
        return newListTicket;
    }

    public TicketDao getTicketDao() {
        return ticketDao;
    }

    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public StationDao getStationDao() {
        return stationDao;
    }

    public void setStationDao(StationDao stationDao) {
        this.stationDao = stationDao;
    }

    public ScheduleDao getScheduleDao() {
        return scheduleDao;
    }

    public void setScheduleDao(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    public TrainDao getTrainDao() {
        return trainDao;
    }

    public void setTrainDao(TrainDao trainDao) {
        this.trainDao = trainDao;
    }
}
