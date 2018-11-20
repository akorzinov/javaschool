package com.korzinov.beans;

import com.korzinov.models.FindTrain;
import com.korzinov.models.TicketTableModel;
import org.springframework.context.annotation.Scope;
import javax.inject.Named;
import java.util.List;

@Named(value = "trainBean")
@Scope("session")
public class TrainBean {

    private String trainName;
    private List<FindTrain> listTrains;
    private List<TicketTableModel> listPassengers;
    private boolean renderTickets;
    private boolean renderTrains = true;
    private boolean renderBackButton;

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public List<FindTrain> getListTrains() {
        return listTrains;
    }

    public void setListTrains(List<FindTrain> listTrains) {
        this.listTrains = listTrains;
    }

    public List<TicketTableModel> getListPassengers() {
        return listPassengers;
    }

    public void setListPassengers(List<TicketTableModel> listPassengers) {
        this.listPassengers = listPassengers;
    }

    public boolean isRenderTickets() {
        return renderTickets;
    }

    public void setRenderTickets(boolean renderTickets) {
        this.renderTickets = renderTickets;
    }

    public boolean isRenderTrains() {
        return renderTrains;
    }

    public void setRenderTrains(boolean renderTrains) {
        this.renderTrains = renderTrains;
    }

    public boolean isRenderBackButton() {
        return renderBackButton;
    }

    public void setRenderBackButton(boolean renderBackButton) {
        this.renderBackButton = renderBackButton;
    }
}
