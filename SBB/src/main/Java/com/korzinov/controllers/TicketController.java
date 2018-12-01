package com.korzinov.controllers;

import com.korzinov.beans.TicketBean;
import com.korzinov.models.TicketTableModel;
import com.korzinov.services.TicketService;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
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

    @Autowired
    private TicketBean ticketBean;

    public List<TicketTableModel> load() {
        ticketBean.setListBoughtTicket(ticketService.listTickets());
        return ticketBean.getListBoughtTicket();
    }

    public void buyTickets() {
        ticketBean.setListTicket(ticketService.buyTickets(ticketBean.getListTicket()));
    }

    public void addPassenger() {
        ticketBean.setListTicket(ticketService.addPassenger(ticketBean.getListTicket(), ticketBean.getFindTrain(), ticketBean.getTicketForTable()));
    }

    public void editTicket(RowEditEvent event) {
        TicketTableModel ticket = (TicketTableModel)event.getObject();
        ticketBean.setListTicket(ticketService.editTicket(ticketBean.getListTicket(), ticket, ticketBean.getOldValue()));
    }

    public void editInit(RowEditEvent event) {
        TicketTableModel oldTicket = new TicketTableModel((TicketTableModel)event.getObject());
        ticketBean.setOldValue(oldTicket);
    }

    public void deleteTicket(TicketTableModel ticket) {
        ticketBean.setListTicket(ticketService.deleteTicket(ticketBean.getListTicket(), ticket));
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public TicketBean getTicketBean() {
        return ticketBean;
    }

    public void setTicketBean(TicketBean ticketBean) {
        this.ticketBean = ticketBean;
    }
}
