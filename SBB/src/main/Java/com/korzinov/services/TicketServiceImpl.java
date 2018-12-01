package com.korzinov.services;

import com.korzinov.dao.*;
import com.korzinov.entities.*;
import com.korzinov.models.FindTrain;
import com.korzinov.models.TicketTableModel;
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

    @Autowired
    private RouteDao routeDao;

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
    public List<TicketTableModel> buyTickets(List<TicketTableModel> listTicket) {

        List<TicketEntity> tickets = listTicketsDb(listTicket);
        TrainEntity train = new TrainEntity();
        TicketEntity ticketInfo = new TicketEntity();
        int freeSeats = 0;
        if (!tickets.isEmpty()) {
            train = tickets.get(0).getTrainByTrainId();
            ticketInfo = tickets.get(0);
        }
        List<ScheduleEntity> listSchedule = scheduleDao.findScheduleByTrain(train);
        if (!listSchedule.isEmpty()) {
            listSchedule = findUniqueScheduleList(train.getTrainName(),ticketInfo.getDepartureTime(),listSchedule);
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
    public List<ScheduleEntity> findUniqueScheduleList(String trainName, Date depTime, List<ScheduleEntity> listSchedule) {
        int countRoute = routeDao.findQuantityRoute(trainName);
        int scheduleId = 0;
        for (int i = 0; i < listSchedule.size(); i++) {
            if (listSchedule.get(i).getDepartureTime().equals(depTime)) {
                scheduleId = listSchedule.get(i).getScheduleId();
                while (i < listSchedule.size()-1 &&
                        (listSchedule.get(i).getRouteByRouteId().getOrderStation() <= listSchedule.get(i+1).getRouteByRouteId().getOrderStation())) {
                    scheduleId++;
                    i++;
                }
            }
        }
        scheduleId = scheduleId - countRoute+1;
        List<ScheduleEntity> result = new ArrayList<>();
        for (int i = 0; i < listSchedule.size(); i++) {
            if (listSchedule.get(i).getScheduleId()==scheduleId) {
                for (int j = 0; j < countRoute; j++) {
                    result.add(listSchedule.get(i));
                    i++;
                }
            }
        }
        return result;
    }

    @Override
    public List<TicketEntity> listTicketsDb(List<TicketTableModel> list) {
        List<TicketEntity> newListTicket = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            TicketEntity ticket = new TicketEntity();
            ticket.setUserByUserId(getUser());
            ticket.setTrainByTrainId(trainDao.findByNameTrainUnique(list.get(i).getTrainName()));
            ticket.setDepartureStationId(stationDao.findByNameStationUnique(list.get(i).getDepartureStationName()).getStationId());
            ticket.setDestinationStationId(stationDao.findByNameStationUnique(list.get(i).getDestinationStationName()).getStationId());
            ticket.setDepartureTime(list.get(i).getDepartureTime());
            ticket.setArrivalTime(list.get(i).getArrivalTime());
            ticket.setFirstName(list.get(i).getFirstName());
            ticket.setLastName(list.get(i).getLastName());
            ticket.setBirthday(list.get(i).getBirthday());
            newListTicket.add(ticket);
        }
        return newListTicket;
    }

    @Override
    public List<TicketTableModel> addPassenger(List<TicketTableModel> listTicket, FindTrain findTrain, TicketTableModel ticketForTable) {

        if (checkSamePassenger(trainDao.findByNameTrainUnique(findTrain.getTrainName()), ticketForTable.getFirstName(),
                ticketForTable.getLastName(), ticketForTable.getBirthday(), findTrain.getDepartureTime())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Specified passenger already registered to this train"));
        } else {
            if (checkSamePassList(listTicket,ticketForTable.getFirstName(), ticketForTable.getLastName(),
                    ticketForTable.getBirthday())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Specified passenger already exist in your list"));
            } else {
                TicketTableModel newPass = new TicketTableModel();
                newPass.setTrainName(findTrain.getTrainName());
                newPass.setDepartureStationName(findTrain.getStationName());
                newPass.setDestinationStationName(findTrain.getStationDest());
                newPass.setDepartureTime(findTrain.getDepartureTime());
                newPass.setArrivalTime(findTrain.getArrivalTime());
                newPass.setFirstName(ticketForTable.getFirstName());
                newPass.setLastName(ticketForTable.getLastName());
                newPass.setBirthday(ticketForTable.getBirthday());
                newPass.setId(listTicket.size());

                listTicket.add(newPass);
                return listTicket;
            }
        }
        return listTicket;
    }

    @Override
    public List<TicketTableModel> editTicket(List<TicketTableModel> listTicket, TicketTableModel ticket, TicketTableModel oldValue) {
        int index = listTicket.indexOf(ticket);
        listTicket.set(index, oldValue);
        if (checkSamePassList(listTicket, ticket.getFirstName(), ticket.getLastName(), ticket.getBirthday())) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Specified passenger already exist in your list"));
            int i = listTicket.indexOf(ticket);
            listTicket.set(i, oldValue);
        } else if (checkSamePassenger(trainDao.findByNameTrainUnique(ticket.getTrainName()), ticket.getFirstName(),
                ticket.getLastName(), ticket.getBirthday(), ticket.getDepartureTime())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Specified passenger already registered to this train"));
        } else {
            int i = listTicket.indexOf(ticket);
            listTicket.set(i, ticket);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ticket Updated"));
            return listTicket;
        }
        return listTicket;
    }

    @Override
    public List<TicketTableModel> deleteTicket(List<TicketTableModel> listTicket, TicketTableModel ticket) {
        int i = listTicket.indexOf(ticket);
        listTicket.remove(i);
        for (int j = i; j < listTicket.size(); j++) {
            listTicket.get(j).setId(listTicket.get(j).getId() - 1);
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Ticket Deleted"));
        return listTicket;
    }

    @Override
    public UserEntity getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        UserEntity user = userDao.findByUserName(userName);
        return user;
    }

    @Override
    public boolean checkSamePassenger(TrainEntity train, String firstName, String lastName, Date birthday, Date depTime) {
        return ticketDao.checkSamePass(train, firstName, lastName, birthday,depTime);
    }

    @Override
    public boolean checkSamePassList(List<TicketTableModel> listTicket, String firstName, String lastName, Date birthday) {
        for (TicketTableModel t: listTicket) {
            if ((t.getFirstName().equals(firstName)) & (t.getLastName().equals(lastName))
                    & (t.getBirthday().equals(birthday))) {
                return true;
            }
        }
        return false;
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

    public RouteDao getRouteDao() {
        return routeDao;
    }

    public void setRouteDao(RouteDao routeDao) {
        this.routeDao = routeDao;
    }
}
