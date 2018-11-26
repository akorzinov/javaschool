package com.korzinov.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "route")
public class RouteEntity {
    private int routeId;
    private int orderStation;
    private Date arrivalTime;
    private Date departureTime;
    private TrainEntity trainByTrainId;
    private StationEntity stationByStationId;

    @Id
    @Column(name = "route_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
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
    @Column(name = "arrival_time", nullable = false)
    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Basic
    @Column(name = "departure_time", nullable = false)
    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteEntity that = (RouteEntity) o;

        if (routeId != that.routeId) return false;
        if (orderStation != that.orderStation) return false;
        if (arrivalTime != null ? !arrivalTime.equals(that.arrivalTime) : that.arrivalTime != null) return false;
        if (departureTime != null ? !departureTime.equals(that.departureTime) : that.departureTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = routeId;
        result = 31 * result + orderStation;
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        return result;
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

    @Override
    public String toString() {
        return "RouteEntity{" +
                "routeId=" + routeId +
                ", orderStation=" + orderStation +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", trainByTrainId=" + trainByTrainId +
                ", stationByStationId=" + stationByStationId +
                '}';
    }
}
