package com.korzinov.dao;

import com.korzinov.entities.FindTrain;
import com.korzinov.entities.ScheduleEntity;
import com.korzinov.entities.StationEntity;
import com.korzinov.entities.TrainEntity;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<FindTrain> findTrainsForUser(String depStation, String destStation, Date date) {

        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<FindTrain> query = cb.createQuery(FindTrain.class);
        Root<ScheduleEntity> schedule = query.from(ScheduleEntity.class);
        Join<ScheduleEntity, StationEntity> station = schedule.join("stationByStationId");
        Join<ScheduleEntity, TrainEntity> train = schedule.join("trainByTrainId");
        Predicate predicate = cb.greaterThanOrEqualTo(schedule.<Date>get("departureTime"), date);
        query.multiselect(train.get("trainName"),station.get("stationName"),schedule.get("departureTime"),
                schedule.get("arrivalTime"), schedule.get("freeSeats"), schedule.get("orderStation"));
        query.where(
                    cb.and(
                            station.get("stationName").in(depStation,destStation), predicate))
                    .orderBy(cb.asc(train.get("trainId")));

        TypedQuery<FindTrain> q = getSession().createQuery(query);
        List<FindTrain> result = q.getResultList();

                    /*удаление одиночных записей*/
        for (int i = 0; i < result.size(); i++) {
            if (result.size()==1) {
                result.remove(0);
                continue;
            }
            if (i == 2 & result.size()==3) {
                result.remove(2);
                continue;
            }
            if (!(i + 2 == result.size())) {
                if (!(result.get(i).getTrainName().equals(result.get(i + 1).getTrainName()))) {
                    result.remove(i);
                    i = i - 2;
                } else if (i + 3 == result.size()) {
                    i--;
                }
                i++;
            } else if (!(result.get(i+1).getTrainName().equals(result.get(i).getTrainName()))) {
                result.remove(i + 1);
                if (!(i==1)){
                    i--;
                }
            } else i++;
        }

                    /*удаление записей неправильного направления*/
        for (int i = 0; i < result.size()-1 ; i+=2) {
            if (result.get(i).getStationName().equals(depStation)) {
                if (result.get(i).getOrderStation() > result.get(i + 1).getOrderStation()) {
                    result.remove(i);
                    result.remove(i);
                    i-=2;
                }
            } else {
                if (result.get(i).getOrderStation() < result.get(i + 1).getOrderStation()) {
                    result.remove(i);
                    result.remove(i);
                    i-=2;
                }
            }

        }
                    /*переправка списка в 1 строку на 1 поезд*/
        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i).getStationName().equals(depStation)) {
                result.get(i).setStationDest(destStation);
                result.get(i).setArrivalTime(result.get(i + 1).getArrivalTime());
                result.remove(i + 1);
            } else {
                result.get(i).setStationName(depStation);
                result.get(i).setStationDest(destStation);
                result.get(i).setDepartureTime(result.get(i + 1).getDepartureTime());
                result.get(i).setFreeSeats(result.get(i + 1).getFreeSeats());
                result.remove(i + 1);
            }
        }

        return result;
    }

    @Override
    public List<FindTrain> findScheduleByStation(String station) {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<FindTrain> query = cb.createQuery(FindTrain.class);
        Root<ScheduleEntity> sc = query.from(ScheduleEntity.class);
        Join<ScheduleEntity, StationEntity> st = sc.join("stationByStationId");
        Join<ScheduleEntity, TrainEntity> tr = sc.join("trainByTrainId");
        query.multiselect(tr.get("trainName"), sc.get("arrivalTime"), sc.get("departureTime"));
        Predicate predicate = cb.like(st.<String>get("stationName"), "%" + station + "%");
        query.where(predicate);
        TypedQuery<FindTrain> q = getSession().createQuery(query);
        List<FindTrain> result = q.getResultList();
        return result;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
