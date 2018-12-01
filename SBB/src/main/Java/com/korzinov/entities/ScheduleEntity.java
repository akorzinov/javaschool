package com.korzinov.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "schedule")
public class ScheduleEntity {
    private int scheduleId;
    private int freeSeats;
    private Date arrivalTime;
    private Date departureTime;
    private RouteEntity routeByRouteId;
    private Integer scheduleIdLast;

    @Id
    @Column(name = "schedule_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
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
    @Temporal(TemporalType.TIMESTAMP)
    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Basic
    @Column(name = "departure_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    @Basic
    @Column(name = "schedule_id_last")
    public Integer getScheduleIdLast() {
        return scheduleIdLast;
    }

    public void setScheduleIdLast(Integer scheduleIdLast) {
        this.scheduleIdLast = scheduleIdLast;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", referencedColumnName = "route_id", nullable = false)
    public RouteEntity getRouteByRouteId() {
        return routeByRouteId;
    }

    public void setRouteByRouteId(RouteEntity routeByRouteId) {
        this.routeByRouteId = routeByRouteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleEntity that = (ScheduleEntity) o;

        if (scheduleId != that.scheduleId) return false;
        if (freeSeats != that.freeSeats) return false;
        if (arrivalTime != null ? !arrivalTime.equals(that.arrivalTime) : that.arrivalTime != null) return false;
        if (departureTime != null ? !departureTime.equals(that.departureTime) : that.departureTime != null)
            return false;
        return routeByRouteId != null ? routeByRouteId.equals(that.routeByRouteId) : that.routeByRouteId == null;
    }

    @Override
    public int hashCode() {
        int result = scheduleId;
        result = 31 * result + freeSeats;
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        result = 31 * result + (routeByRouteId != null ? routeByRouteId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ScheduleEntity{" +
                "scheduleId=" + scheduleId +
                ", freeSeats=" + freeSeats +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", routeByRouteId=" + routeByRouteId +
                ", scheduleIdLast=" + scheduleIdLast +
                '}';
    }
}
