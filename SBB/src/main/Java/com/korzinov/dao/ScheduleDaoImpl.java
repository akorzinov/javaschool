package com.korzinov.dao;

import com.korzinov.entities.*;
import com.korzinov.models.FindTrain;
import com.korzinov.models.RouteModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {

    static final Logger logger = LogManager.getLogger(ScheduleDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<FindTrain> findTrainsForUser(String depStation, String destStation, Date date) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<FindTrain> query = cb.createQuery(FindTrain.class);
            Root<ScheduleEntity> sc = query.from(ScheduleEntity.class);
            Join<ScheduleEntity, StationEntity> st = sc.join("stationByStationId");
            Join<ScheduleEntity, TrainEntity> tr = sc.join("trainByTrainId");
            Predicate predicate1 = cb.greaterThanOrEqualTo(sc.<Date>get("departureTime"), date);
            Predicate predicate2 = cb.like(st.<String>get("stationName"), "%" + depStation + "%");
            Predicate predicate3 = cb.like(st.<String>get("stationName"), "%" + destStation + "%");
            query.multiselect(tr.get("trainName"), st.get("stationName"), sc.get("departureTime"),
                    sc.get("arrivalTime"), sc.get("freeSeats"), sc.get("orderStation"));
            query.where(cb.or(cb.and(predicate1, predicate2),predicate3))
                    .orderBy(cb.asc(tr.get("trainId")));
            TypedQuery<FindTrain> q = getSession().createQuery(query);
            List<FindTrain> result = q.getResultList();
            for (FindTrain r : result) {
                logger.info("FindTrain: " + r);
            }
            return result;
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<FindTrain> findScheduleByStation(String station) {
        try {
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
            for (FindTrain r : result) {
                logger.info("FindTrain: " + r);
            }
            return result;
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<FindTrain> findScheduleByStationAndDate(String station, Date date) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<FindTrain> query = cb.createQuery(FindTrain.class);
            Root<ScheduleEntity> sc = query.from(ScheduleEntity.class);
            Join<ScheduleEntity, StationEntity> st = sc.join("stationByStationId");
            Join<ScheduleEntity, TrainEntity> tr = sc.join("trainByTrainId");
            query.multiselect(tr.get("trainName"), sc.get("arrivalTime"), sc.get("departureTime"));
            Predicate predicate = cb.and(cb.equal(st.get("stationName"), station),
                                    cb.greaterThanOrEqualTo(sc.<Date>get("arrivalTime"), date));
            query.where(predicate);
            TypedQuery<FindTrain> q = getSession().createQuery(query);
            List<FindTrain> result = q.getResultList();
            for (FindTrain r : result) {
                logger.info("FindTrain: " + r);
            }
            return result;
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
            return null;
        }
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
            query.where(cb.like(tr.<String>get("trainName"),"%" + trainName + "%")).orderBy(cb.asc(sc.get("orderStation")));
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
    public List<ScheduleEntity> findScheduleByTrain(TrainEntity train) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<ScheduleEntity> query = cb.createQuery(ScheduleEntity.class);
            Root<ScheduleEntity> sc = query.from(ScheduleEntity.class);
            query.select(sc).where(cb.equal(sc.get("trainByTrainId"), train));
            Query<ScheduleEntity> q = getSession().createQuery(query);
            List<ScheduleEntity> result = q.getResultList();
            for (ScheduleEntity s : result) {
                logger.info("Schedule list: " + s);
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

    @Override
    public void updateFreeSeats(List<ScheduleEntity> listSchedule) {
        for (ScheduleEntity s : listSchedule) {
            try {
                getSession().update(s);
                logger.info("Free seats successfully update, Train: " + s.getTrainByTrainId().getTrainName() +
                        " FreeSeats: " + s.getFreeSeats());
            } catch (HibernateException e) {
                logger.error("Hibernate exception " + e.getMessage());
            }
        }
    }

    @Override
    public List<FindTrain> findTrainDetails(String trainName) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();

            CriteriaQuery<Integer> subQuery = cb.createQuery(Integer.class);
            Root<ScheduleEntity> sc1 = subQuery.from(ScheduleEntity.class);
            Join<ScheduleEntity, TrainEntity> tr1 = sc1.join("trainByTrainId");
            subQuery.select(cb.max(sc1.<Integer>get("orderStation")));
            subQuery.where(cb.like(tr1.<String>get("trainName"),"%" + trainName + "%"));
            TypedQuery<Integer> q1 = getSession().createQuery(subQuery);
            Integer maxOrderStation = q1.getSingleResult();

            CriteriaQuery<FindTrain> query = cb.createQuery(FindTrain.class);
            Root<ScheduleEntity> sc = query.from(ScheduleEntity.class);
            Join<ScheduleEntity, StationEntity> st = sc.join("stationByStationId");
            Join<ScheduleEntity, TrainEntity> tr = sc.join("trainByTrainId");
            query.multiselect(tr.get("trainName"), st.get("stationName"), sc.get("orderStation"));
            Predicate predicate1 = cb.like(tr.<String>get("trainName"), "%" + trainName + "%");
            Predicate predicate2 = sc.get("orderStation").in(Arrays.asList(1, maxOrderStation));
            query.where(cb.and(predicate1, predicate2));
            TypedQuery<FindTrain> q = getSession().createQuery(query);
            List<FindTrain> result = q.getResultList();
            for (FindTrain f : result) {
                logger.info("Train list: " + f);
            }
            return result;
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<FindTrain> findTrainDetailsUnique(String trainName) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();

            CriteriaQuery<Integer> subQuery = cb.createQuery(Integer.class);
            Root<ScheduleEntity> sc1 = subQuery.from(ScheduleEntity.class);
            Join<ScheduleEntity, TrainEntity> tr1 = sc1.join("trainByTrainId");
            subQuery.select(cb.max(sc1.<Integer>get("orderStation")));
            subQuery.where(cb.equal(tr1.<String>get("trainName"),trainName));
            TypedQuery<Integer> q1 = getSession().createQuery(subQuery);
            Integer maxOrderStation = q1.getSingleResult();

            CriteriaQuery<FindTrain> query = cb.createQuery(FindTrain.class);
            Root<ScheduleEntity> sc = query.from(ScheduleEntity.class);
            Join<ScheduleEntity, StationEntity> st = sc.join("stationByStationId");
            Join<ScheduleEntity, TrainEntity> tr = sc.join("trainByTrainId");
            query.multiselect(tr.get("trainName"), st.get("stationName"), sc.get("orderStation"));
            Predicate predicate1 = cb.equal(tr.<String>get("trainName"), trainName);
            Predicate predicate2 = sc.get("orderStation").in(Arrays.asList(1, maxOrderStation));
            query.where(cb.and(predicate1, predicate2));
            TypedQuery<FindTrain> q = getSession().createQuery(query);
            List<FindTrain> result = q.getResultList();
            logger.info("Train : " + result);
            return result;
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<FindTrain> findTrainDetailsAll() {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<FindTrain> query = cb.createQuery(FindTrain.class);
            Root<ScheduleEntity> sc = query.from(ScheduleEntity.class);
            Join<ScheduleEntity, StationEntity> st = sc.join("stationByStationId");
            Join<ScheduleEntity, TrainEntity> tr = sc.join("trainByTrainId");
            query.multiselect(tr.get("trainName"), st.get("stationName"), sc.get("orderStation"));
            query.where(cb.equal(sc.get("orderStation"), "1"));
            TypedQuery<FindTrain> q = getSession().createQuery(query);
            List<FindTrain> result = q.getResultList();
            for (FindTrain f : result) {
                logger.info("Train list: " + f);
            }
            return result;
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
            return null;
        }
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
