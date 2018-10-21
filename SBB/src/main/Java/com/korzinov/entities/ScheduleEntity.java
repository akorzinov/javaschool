package com.korzinov.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "schedule")
public class ScheduleEntity {
    private int recordId;
    private int orderStation;
    private int freeSeats;
    private Timestamp arrivalTime;
    private Timestamp departureTime;
    private TrainEntity trainByTrainId;
    private StationEntity stationByStationId;

    @Id
    @Column(name = "record_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    @Basic
    @Column(name = "order_station", nullable = false)
    public int getOrderStation() {
        return orderStation;
    }

    public void setOrderStation(int orderStation) {
        this.orderStation = orderStation;
    }

    @Basic
    @Column(name = "free_seats", nullable = false)
    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    @Basic
    @Column(name = "arrival_time", nullable = false)
    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Basic
    @Column(name = "departure_time", nullable = false)
    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleEntity that = (ScheduleEntity) o;

        if (recordId != that.recordId) return false;
        if (orderStation != that.orderStation) return false;
        if (freeSeats != that.freeSeats) return false;
        if (arrivalTime != null ? !arrivalTime.equals(that.arrivalTime) : that.arrivalTime != null) return false;
        if (departureTime != null ? !departureTime.equals(that.departureTime) : that.departureTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = recordId;
        result = 31 * result + orderStation;
        result = 31 * result + freeSeats;
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ScheduleEntity{" +
                "recordId=" + recordId +
                ", orderStation=" + orderStation +
                ", freeSeats=" + freeSeats +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", trainByTrainId=" + trainByTrainId +
                ", stationByStationId=" + stationByStationId +
                '}';
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id", referencedColumnName = "train_id", nullable = false)
    public TrainEntity getTrainByTrainId() {
        return trainByTrainId;
    }

    public void setTrainByTrainId(TrainEntity trainByTrainId) {
        this.trainByTrainId = trainByTrainId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id", referencedColumnName = "station_id", nullable = false)
    public StationEntity getStationByStationId() {
        return stationByStationId;
    }

    public void setStationByStationId(StationEntity stationByStationId) {
        this.stationByStationId = stationByStationId;
    }
}
