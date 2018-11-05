package com.korzinov.controllers;

import com.korzinov.entities.FindTrain;
import com.korzinov.entities.TicketEntity;
import com.korzinov.entities.TicketTableModel;
import com.korzinov.services.StationService;
import com.korzinov.services.TicketService;
import com.korzinov.services.TrainService;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
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
    private List<TicketTableModel> listBoughtTicket = new ArrayList<>();

    public List<TicketTableModel> load() {
        return listBoughtTicket = ticketService.listTickets();
    }

    public String buyTickets() {
        listTicket = ticketService.buyTickets(ticketService.listTicketsDb(listTicket), listTicket);
        return null;
    }

    public void addPassenger() {
        TicketTableModel newPass = new TicketTableModel();
        newPass.setTrainName(findTrain.getTrainName());
        newPass.setDepartureStationName(findTrain.getStationName());
        newPass.setDestinationStationName(findTrain.getStationDest());
        newPass.setDepartureTime(findTrain.getDepartureTime());
        newPass.setArrivalTime(findTrain.getArrivalTime());
        newPass.setFirstName(ticketForTable.getFirstName());
        newPass.setLastName(ticketForTable.getLastName());
        newPass.setBirthday(ticketForTable.getBirthday());
        newPass.setId(listTicket.size() + 1);

//        for (TicketTableModel t: listTicket) {
//            if ((t.getFirstName().equals(ticketForTable.getFirstName())) && (t.getLastName().equals(ticketForTable.getLastName()))
//                    && (t.getBirthday() == ticketForTable.getBirthday())) {
//
//            }
//        }

        listTicket.add(newPass);
    }



        /*добавить проверку пссле редактирования на одинаковых пассажиров*/
    public void editTicket(RowEditEvent event) {
        TicketTableModel ticket = (TicketTableModel)event.getObject();
        int i = listTicket.indexOf(ticket);
        listTicket.set(i,ticket);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Ticket Updated"));
    }

    public String deleteTicket(TicketTableModel ticket) {
        int i = listTicket.indexOf(ticket);
        listTicket.remove(i);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Ticket Deleted"));
        return null;
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

    public List<TicketTableModel> getListBoughtTicket() {
        return listBoughtTicket;
    }

    public void setListBoughtTicket(List<TicketTableModel> listBoughtTicket) {
        this.listBoughtTicket = listBoughtTicket;
    }
}
