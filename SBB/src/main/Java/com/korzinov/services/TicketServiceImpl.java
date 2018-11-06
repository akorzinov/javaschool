package com.korzinov.services;

import com.korzinov.beans.TicketBean;
import com.korzinov.dao.*;
import com.korzinov.entities.*;
import org.primefaces.component.roweditor.RowEditorRenderer;
import org.primefaces.event.RowEditEvent;
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
    private TicketBean ticketBean;

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
        ticketBean.setListBoughtTicket(listBoughtTickets);

        return ticketBean.getListBoughtTicket();
    }

    @Override
    public void buyTickets() {

        List<TicketTableModel> listTicket = ticketBean.getListTicket();
        List<TicketEntity> tickets = listTicketsDb(listTicket);

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
            ticketBean.setListTicket(listTicket);
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Sorry, there are only " + freeSeats + " free seats and you want to book " +
                     tickets.size() + " tickets, you can find other train"));
            ticketBean.setListTicket(listTicket);
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

    @Override
    public void addPassenger() {

        if (checkSamePassenger(trainDao.findByNameTrain(ticketBean.getFindTrain().getTrainName()).get(0),
            ticketBean.getTicketForTable().getFirstName(), ticketBean.getTicketForTable().getLastName(),
            ticketBean.getTicketForTable().getBirthday())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Specified passenger already registered to this train"));
        } else {
            if (checkSamePassList(ticketBean.getListTicket(),ticketBean.getTicketForTable().getFirstName(),
                    ticketBean.getTicketForTable().getLastName(), ticketBean.getTicketForTable().getBirthday())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Specified passenger already exist in your list"));
            } else {
                TicketTableModel newPass = new TicketTableModel();
                newPass.setTrainName(ticketBean.getFindTrain().getTrainName());
                newPass.setDepartureStationName(ticketBean.getFindTrain().getStationName());
                newPass.setDestinationStationName(ticketBean.getFindTrain().getStationDest());
                newPass.setDepartureTime(ticketBean.getFindTrain().getDepartureTime());
                newPass.setArrivalTime(ticketBean.getFindTrain().getArrivalTime());
                newPass.setFirstName(ticketBean.getTicketForTable().getFirstName());
                newPass.setLastName(ticketBean.getTicketForTable().getLastName());
                newPass.setBirthday(ticketBean.getTicketForTable().getBirthday());
                newPass.setId(ticketBean.getListTicket().size());

                ticketBean.getListTicket().add(newPass);
            }
        }
    }

    @Override
    public void editTicket(RowEditEvent event) {
        TicketTableModel ticket = (TicketTableModel)event.getObject();
        int index = ticketBean.getListTicket().indexOf(ticket);
        ticketBean.getListTicket().set(index, ticketBean.getOldValue());
        if (checkSamePassList(ticketBean.getListTicket(), ticket.getFirstName(), ticket.getLastName(), ticket.getBirthday())) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Specified passenger already exist in your list"));
            int i = ticketBean.getListTicket().indexOf(ticket);
            ticketBean.getListTicket().set(i, ticketBean.getOldValue());
        } else if (checkSamePassenger(trainDao.findByNameTrain(ticket.getTrainName()).get(0), ticket.getFirstName(),
                ticket.getLastName(), ticket.getBirthday())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Specified passenger already registered to this train"));
        } else {
            int i = ticketBean.getListTicket().indexOf(ticket);
            ticketBean.getListTicket().set(i, ticket);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ticket Updated"));
        }
    }

    @Override
    public void editInit(RowEditEvent event) {
        TicketTableModel oldTicket = new TicketTableModel((TicketTableModel)event.getObject());
        ticketBean.setOldValue(oldTicket);
    }

    @Override
    public String deleteTicket(TicketTableModel ticket) {
        int i = ticketBean.getListTicket().indexOf(ticket);
        ticketBean.getListTicket().remove(i);
        for (int j = i; j < ticketBean.getListTicket().size(); j++) {
            ticketBean.getListTicket().get(j).setId(ticketBean.getListTicket().get(j).getId() - 1);
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Ticket Deleted"));
        return null;
    }

    @Override
    public UserEntity getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        UserEntity user = userDao.findByUserName(userName);
        return user;
    }

    @Override
    public boolean checkSamePassenger(TrainEntity train, String firstName, String lastName, Date birthday) {
        return ticketDao.checkSamePass(train, firstName, lastName, birthday);
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

    public TicketBean getTicketBean() {
        return ticketBean;
    }

    public void setTicketBean(TicketBean ticketBean) {
        this.ticketBean = ticketBean;
    }
}
