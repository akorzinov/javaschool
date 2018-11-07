package com.korzinov.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "station")
public class StationEntity {
    private int stationId;
    private String stationName;
    private Set<ScheduleEntity> schedulesByStationId;

    @Id
    @Column(name = "station_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    @Basic
    @Column(name = "station_name", nullable = false, length = 50)
    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StationEntity that = (StationEntity) o;

        if (stationId != that.stationId) return false;
        if (stationName != null ? !stationName.equals(that.stationName) : that.stationName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stationId;
        result = 31 * result + (stationName != null ? stationName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StationEntity{" +
                "stationId=" + stationId +
                ", stationName='" + stationName + '\'' +
                '}';
    }

    @OneToMany(mappedBy = "stationByStationId", fetch = FetchType.LAZY)
    public Set<ScheduleEntity> getSchedulesByStationId() {
        return schedulesByStationId;
    }

    public void setSchedulesByStationId(Set<ScheduleEntity> schedulesByStationId) {
        this.schedulesByStationId = schedulesByStationId;
    }
}
