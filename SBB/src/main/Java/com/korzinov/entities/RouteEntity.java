package com.korzinov.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "route")
public class RouteEntity {
    private int routeId;
    private int orderStation;
    private TrainEntity trainByTrainId;
    private StationEntity stationByStationId;
    private Set<ScheduleEntity> schedulesByRouteId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteEntity that = (RouteEntity) o;

        if (routeId != that.routeId) return false;
        if (orderStation != that.orderStation) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = routeId;
        result = 31 * result + orderStation;
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

    @OneToMany(mappedBy = "routeByRouteId", fetch = FetchType.LAZY)
    public Set<ScheduleEntity> getSchedulesByRouteId() {
        return schedulesByRouteId;
    }

    public void setSchedulesByRouteId(Set<ScheduleEntity> schedulesByRouteId) {
        this.schedulesByRouteId = schedulesByRouteId;
    }

    @Override
    public String toString() {
        return "RouteEntity{" +
                "routeId=" + routeId +
                ", orderStation=" + orderStation +
                ", trainByTrainId=" + trainByTrainId +
                ", stationByStationId=" + stationByStationId +
                '}';
    }
}
