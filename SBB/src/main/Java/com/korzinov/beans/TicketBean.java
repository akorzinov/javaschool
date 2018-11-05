package com.korzinov.beans;

import com.korzinov.entities.FindTrain;
import com.korzinov.entities.TicketTableModel;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named(value = "ticketBean")
public class TicketBean {

    private List<TicketTableModel> listTicket = new ArrayList<>();
    private FindTrain findTrain = new FindTrain();
    private TicketTableModel ticketForTable = new TicketTableModel();
    private List<TicketTableModel> listBoughtTicket = new ArrayList<>();
    private TicketTableModel oldValue = new TicketTableModel();


    public List<TicketTableModel> getListTicket() {
        return listTicket;
    }

    public void setListTicket(List<TicketTableModel> listTicket) {
        this.listTicket = listTicket;
    }

    public FindTrain getFindTrain() {
        return findTrain;
    }

    public void setFindTrain(FindTrain findTrain) {
        this.findTrain = findTrain;
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

    public TicketTableModel getOldValue() {
        return oldValue;
    }

    public void setOldValue(TicketTableModel oldValue) {
        this.oldValue = oldValue;
    }
}
