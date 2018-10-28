package com.korzinov.dao;

import com.korzinov.entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
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

    static final Logger logger = LogManager.getLogger(ScheduleDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<FindTrain> findTrainsForUser(String depStation, String destStation, Date date) {

        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<FindTrain> query = cb.createQuery(FindTrain.class);
        Root<ScheduleEntity> sc = query.from(ScheduleEntity.class);
        Join<ScheduleEntity, StationEntity> st = sc.join("stationByStationId");
        Join<ScheduleEntity, TrainEntity> tr = sc.join("trainByTrainId");
        Predicate predicate = cb.greaterThanOrEqualTo(sc.<Date>get("departureTime"), date);
        Predicate predicate2 = cb.or(cb.like(st.<String>get("stationName"),"%" + depStation + "%"),
                                    cb.like(st.<String>get("stationName"),"%" + destStation + "%"));
        query.multiselect(tr.get("trainName"),st.get("stationName"),sc.get("departureTime"),
                sc.get("arrivalTime"), sc.get("freeSeats"), sc.get("orderStation"));
        query.where(
                    cb.and(predicate2, predicate))
//                            cb.and(st.get("stationName").in(depStation,destStation), predicate))
                    .orderBy(cb.asc(tr.get("trainId")));

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

    @Override
    public List<RouteModel> findRoute(String trainName) {

        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<RouteModel> query = cb.createQuery(RouteModel.class);
            Root<ScheduleEntity> sc = query.from(ScheduleEntity.class);
            Join<ScheduleEntity, StationEntity> st = sc.join("stationByStationId");
            Join<ScheduleEntity, TrainEntity> tr = sc.join("trainByTrainId");
            query.multiselect(sc.get("recordId"),sc.get("freeSeats"), tr.get("trainName"),
                    tr.get("quantitySeats"),sc.get("orderStation"),st.get("stationName"),
                    sc.get("arrivalTime"), sc.get("departureTime"));
            query.where(cb.equal(tr.get("trainName"),trainName));
            TypedQuery<RouteModel> q = getSession().createQuery(query);
            List<RouteModel> result = q.getResultList();
            for (RouteModel r : result) {
                logger.info("Route model: " + r);
            }
            return result;
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
            return null;
        }
    }

    @Override
    public void addRoute(ScheduleEntity schedule) {
        try {
            getSession().save(schedule);
            logger.info("Route successfully saved, Route: " + schedule);
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
        }

    }

    @Override
    public void updateRoute(ScheduleEntity schedule) {
        try {
            getSession().update(schedule);
            logger.info("Route successfully update, Route: " + schedule);
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
        }
    }

    @Override
    public void deleteRoute(ScheduleEntity schedule) {
        try {
            getSession().delete(schedule);
            logger.info("Route successfully delete, Route: " + schedule);
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
        }
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
