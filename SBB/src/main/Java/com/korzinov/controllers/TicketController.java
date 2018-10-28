package com.korzinov.controllers;

import com.korzinov.entities.FindTrain;
import com.korzinov.entities.TicketEntity;
import com.korzinov.entities.TicketTableModel;
import com.korzinov.services.StationService;
import com.korzinov.services.TicketService;
import com.korzinov.services.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named(value = "ticketController")
@SessionScoped
@Controller
public class TicketController  implements Serializable{

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private StationService stationService;

    private List<TicketTableModel> listTicket = new ArrayList<>();
    private FindTrain findTrain = new FindTrain();
    private TicketTableModel ticketForTable = new TicketTableModel();


    public String loadTickets() {
//        listTicket = ticketService.listTickets();
        return null;
    }

    public String buyTickets() {
        ticketService.buyTickets(listTicketsDb(listTicket));
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Tickets has been booked successfully"));
        listTicket.clear();
        return null;
    }

    public String addPassenger() {
//        TicketEntity newTicket = new TicketEntity();
//        newTicket.setUserByUserId(ticketService.getUser());
//        newTicket.setTrainByTrainId(trainService.findByNameTrain(findTrain.getTrainName()).get(0));
//        System.out.println("train" + newTicket.getTrainByTrainId());
//        newTicket.setDepartureStationId(stationService.findIdByStationName(findTrain.getStationName()));
//        System.out.println("depID" + newTicket.getDepartureStationId());
//        newTicket.setDestinationStationId(stationService.findIdByStationName(findTrain.getStationDest()));
//        System.out.println("destID" + newTicket.getDestinationStationId());
//        newTicket.setDepartureTime(findTrain.getDepartureTime());
//        System.out.println("deptime" + newTicket.getDepartureTime());
//        newTicket.setArrivalTime(findTrain.getArrivalTime());
//        System.out.println("artime" + newTicket.getArrivalTime());
//        newTicket.setFirstName(ticket.getFirstName());
//        System.out.println("fname" + newTicket.getFirstName());
//        newTicket.setLastName(ticket.getLastName());
//        System.out.println("lname" + newTicket.getLastName());
//        newTicket.setBirthday(ticket.getBirthday());
//        System.out.println("bday" + newTicket.getBirthday());
//        listTicket.add(newTicket);
//        System.out.println("ticket" + listTicket.get(0));
        TicketTableModel newrow = new TicketTableModel();
        newrow.setTrainName(findTrain.getTrainName());
        newrow.setDepartureStationName(findTrain.getStationName());
        newrow.setDestinationStationName(findTrain.getStationDest());
        newrow.setDepartureTime(findTrain.getDepartureTime());
        newrow.setArrivalTime(findTrain.getArrivalTime());
        newrow.setFirstName(ticketForTable.getFirstName());
        newrow.setLastName(ticketForTable.getLastName());
        newrow.setBirthday(ticketForTable.getBirthday());

//        ticketForTable.setTrainName(findTrain.getTrainName());
//        ticketForTable.setDepartureStationName(findTrain.getStationName());
//        ticketForTable.setDestinationStationName(findTrain.getStationDest());
//        ticketForTable.setDepartureTime(findTrain.getDepartureTime());
//        ticketForTable.setArrivalTime(findTrain.getArrivalTime());
//        listTicket.add(ticketForTable);
        listTicket.add(newrow);
        return null;
    }

    public List<TicketEntity> listTicketsDb(List<TicketTableModel> list) {
        List<TicketEntity> newListTicket = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            TicketEntity ticket = new TicketEntity();
            ticket.setUserByUserId(ticketService.getUser());
            ticket.setTrainByTrainId(trainService.findByNameTrain(list.get(i).getTrainName()).get(0));
            ticket.setDepartureStationId(stationService.findByNameStation(list.get(i).getDepartureStationName()).get(0).getStationId());
            ticket.setDestinationStationId(stationService.findByNameStation(list.get(i).getDestinationStationName()).get(0).getStationId());
            ticket.setDepartureTime(list.get(i).getDepartureTime());
            ticket.setArrivalTime(list.get(i).getArrivalTime());
            ticket.setFirstName(list.get(i).getFirstName());
            ticket.setLastName(list.get(i).getLastName());
            ticket.setBirthday(list.get(i).getBirthday());
            newListTicket.add(ticket);
        }
        return newListTicket;
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public FindTrain getFindTrain() {
        return findTrain;
    }

    public void setFindTrain(FindTrain findTrain) {
        this.findTrain = findTrain;
    }

    public TrainService getTrainService() {
        return trainService;
    }

    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    public StationService getStationService() {
        return stationService;
    }

    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    public void setListTicket(List<TicketTableModel> listTicket) {
        this.listTicket = listTicket;
    }

    public List<TicketTableModel> getListTicket() {
        return listTicket;
    }

    public TicketTableModel getTicketForTable() {
        return ticketForTable;
    }

    public void setTicketForTable(TicketTableModel ticketForTable) {
        this.ticketForTable = ticketForTable;
    }
}
