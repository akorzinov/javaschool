package com.korzinov.beans;

import com.korzinov.models.FindTrain;
import com.korzinov.models.RouteModel;
import com.korzinov.models.ScheduleModel;
import com.korzinov.models.TrainModel;
import org.springframework.context.annotation.Scope;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Named(value = "scheduleBean")
@Scope("session")
public class ScheduleBean {

    private String depStation;
    private String destStation;
    private Date date = Calendar.getInstance().getTime();
    private List<FindTrain> listFoundTrains = new ArrayList<>();
    private String station;
    private List<FindTrain> listSchedule = new ArrayList<>();
    private String trainName;
    private String trainNameRoute;
    private List<RouteModel> listSchedules = new ArrayList<>();
    private ScheduleModel schedule = new ScheduleModel();
    private TrainModel train = new TrainModel();
    private String stationName;
    private List<TrainModel> listTrain  = new ArrayList<>();
    private List<RouteModel> listScheduleTrain;
    private boolean renderFoundStationTable = true;
    private boolean renderTrainDetails;
    private boolean renderBackButton;
    private boolean renderConfigScheduleTable1;
    private boolean renderConfigScheduleTable2;
    private boolean renderAddScheduleButton;

    public String getDepStation() {
        return depStation;
    }

    public void setDepStation(String depStation) {
        this.depStation = depStation;
    }

    public String getDestStation() {
        return destStation;
    }

    public void setDestStation(String destStation) {
        this.destStation = destStation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<FindTrain> getListFoundTrains() {
        return listFoundTrains;
    }

    public void setListFoundTrains(List<FindTrain> listFoundTrains) {
        this.listFoundTrains = listFoundTrains;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public List<FindTrain> getListSchedule() {
        return listSchedule;
    }

    public void setListSchedule(List<FindTrain> listSchedule) {
        this.listSchedule = listSchedule;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public List<RouteModel> getListSchedules() {
        return listSchedules;
    }

    public void setListSchedules(List<RouteModel> listSchedules) {
        this.listSchedules = listSchedules;
    }

    public ScheduleModel getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleModel schedule) {
        this.schedule = schedule;
    }

    public TrainModel getTrain() {
        return train;
    }

    public void setTrain(TrainModel train) {
        this.train = train;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public List<TrainModel> getListTrain() {
        return listTrain;
    }

    public void setListTrain(List<TrainModel> listTrain) {
        this.listTrain = listTrain;
    }

    public List<RouteModel> getListScheduleTrain() {
        return listScheduleTrain;
    }

    public void setListScheduleTrain(List<RouteModel> listScheduleTrain) {
        this.listScheduleTrain = listScheduleTrain;
    }

    public boolean isRenderFoundStationTable() {
        return renderFoundStationTable;
    }

    public void setRenderFoundStationTable(boolean renderFoundStationTable) {
        this.renderFoundStationTable = renderFoundStationTable;
    }

    public boolean isRenderTrainDetails() {
        return renderTrainDetails;
    }

    public void setRenderTrainDetails(boolean renderTrainDetails) {
        this.renderTrainDetails = renderTrainDetails;
    }

    public boolean isRenderBackButton() {
        return renderBackButton;
    }

    public void setRenderBackButton(boolean renderBackButton) {
        this.renderBackButton = renderBackButton;
    }

    public String getTrainNameRoute() {
        return trainNameRoute;
    }

    public void setTrainNameRoute(String trainNameRoute) {
        this.trainNameRoute = trainNameRoute;
    }

    public boolean isRenderConfigScheduleTable1() {
        return renderConfigScheduleTable1;
    }

    public void setRenderConfigScheduleTable1(boolean renderConfigScheduleTable1) {
        this.renderConfigScheduleTable1 = renderConfigScheduleTable1;
    }

    public boolean isRenderConfigScheduleTable2() {
        return renderConfigScheduleTable2;
    }

    public void setRenderConfigScheduleTable2(boolean renderConfigScheduleTable2) {
        this.renderConfigScheduleTable2 = renderConfigScheduleTable2;
    }

    public boolean isRenderAddScheduleButton() {
        return renderAddScheduleButton;
    }

    public void setRenderAddScheduleButton(boolean renderAddScheduleButton) {
        this.renderAddScheduleButton = renderAddScheduleButton;
    }
}
