package com.korzinov.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "train")
public class TrainEntity {
    private int trainId;
    private String trainName;
    private int quantitySeats;
    private Set<ScheduleEntity> schedulesByTrainId;
    private Set<TicketEntity> ticketsByTrainId;

    @Id
    @Column(name = "train_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    @Basic
    @Column(name = "train_name", nullable = false, length = 50)
    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    @Basic
    @Column(name = "quantity_seats", nullable = false)
    public int getQuantitySeats() {
        return quantitySeats;
    }

    public void setQuantitySeats(int quantitySeats) {
        this.quantitySeats = quantitySeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainEntity that = (TrainEntity) o;

        if (trainId != that.trainId) return false;
        if (quantitySeats != that.quantitySeats) return false;
        if (trainName != null ? !trainName.equals(that.trainName) : that.trainName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = trainId;
        result = 31 * result + (trainName != null ? trainName.hashCode() : 0);
        result = 31 * result + quantitySeats;
        return result;
    }

    @Override
    public String toString() {
        return "TrainEntity{" +
                "trainId=" + trainId +
                ", trainName='" + trainName + '\'' +
                ", quantitySeats=" + quantitySeats +
                ", schedulesByTrainId=" + schedulesByTrainId +
                ", ticketsByTrainId=" + ticketsByTrainId +
                '}';
    }

    @OneToMany(mappedBy = "trainByTrainId", fetch = FetchType.LAZY)
    public Set<ScheduleEntity> getSchedulesByTrainId() {
        return schedulesByTrainId;
    }

    public void setSchedulesByTrainId(Set<ScheduleEntity> schedulesByTrainId) {
        this.schedulesByTrainId = schedulesByTrainId;
    }

    @OneToMany(mappedBy = "trainByTrainId", fetch = FetchType.LAZY)
    public Set<TicketEntity> getTicketsByTrainId() {
        return ticketsByTrainId;
    }

    public void setTicketsByTrainId(Set<TicketEntity> ticketsByTrainId) {
        this.ticketsByTrainId = ticketsByTrainId;
    }
}
