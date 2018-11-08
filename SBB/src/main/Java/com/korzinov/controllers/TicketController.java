package com.korzinov.controllers;

import com.korzinov.models.TicketTableModel;
import com.korzinov.services.TicketService;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named(value = "ticketController")
@SessionScoped
@Controller
public class TicketController  implements Serializable{

    @Autowired
    private TicketService ticketService;

    public List<TicketTableModel> load() {
        return ticketService.listTickets();
    }

    public void buyTickets() {
        ticketService.buyTickets();
    }

    public void addPassenger() {
        ticketService.addPassenger();
    }

    public void editTicket(RowEditEvent event) {
        ticketService.editTicket(event);
    }

    public void editInit(RowEditEvent event) {
        ticketService.editInit(event);
    }

    public void deleteTicket(TicketTableModel ticket) {
        ticketService.deleteTicket(ticket);
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

}
