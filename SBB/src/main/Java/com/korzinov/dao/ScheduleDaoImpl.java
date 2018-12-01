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


    @Override                     /*+*/
    public List<FindTrain> findTrainsForUser(String depStation, String destStation, Date date) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<FindTrain> query = cb.createQuery(FindTrain.class);
            Root<ScheduleEntity> sc = query.from(ScheduleEntity.class);
            Join<ScheduleEntity, RouteEntity> rt = sc.join("routeByRouteId");
            Join<RouteEntity, StationEntity> st = rt.join("stationByStationId");
            Join<RouteEntity, TrainEntity> tr = rt.join("trainByTrainId");
            Predicate predicate1 = cb.greaterThanOrEqualTo(sc.<Date>get("departureTime"), date);
            Predicate predicate2 = cb.equal(st.get("stationName"), depStation);
            Predicate predicate3 = cb.equal(st.get("stationName"), destStation);
            query.multiselect(tr.get("trainName"), st.get("stationName"), sc.get("departureTime"),
                    sc.get("arrivalTime"), sc.get("freeSeats"), rt.get("orderStation"), sc.get("scheduleId"));
            query.where(cb.or(cb.and(predicate1, predicate2),cb.and(predicate1, predicate3)))
                    .orderBy(cb.asc(sc.get("scheduleId")));
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

    @Override                       /*+*/
    public List<FindTrain> findScheduleByStation(String station) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<FindTrain> query = cb.createQuery(FindTrain.class);
            Root<ScheduleEntity> sc = query.from(ScheduleEntity.class);
            Join<ScheduleEntity, RouteEntity> rt = sc.join("routeByRouteId");
            Join<RouteEntity, StationEntity> st = rt.join("stationByStationId");
            Join<RouteEntity, TrainEntity> tr = rt.join("trainByTrainId");
            query.multiselect(tr.get("trainName"), sc.get("arrivalTime"), sc.get("departureTime"));
            Predicate predicate = cb.equal(st.get("stationName"), station);
            query.where(predicate).orderBy(cb.asc(sc.get("arrivalTime")));
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

    @Override                 /*+*/
    public List<RouteModel> findSchedule(String trainName, Date date) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();

            CriteriaQuery<ScheduleEntity> subQuery = cb.createQuery(ScheduleEntity.class);
            Root<ScheduleEntity> sc1 = subQuery.from(ScheduleEntity.class);
            Join<ScheduleEntity, RouteEntity> rt1 = sc1.join("routeByRouteId");
            Join<RouteEntity, TrainEntity> tr1 = rt1.join("trainByTrainId");
            Date nextDay = new Date(date.getTime() + 24*60*60*1000);
            Predicate predicate1 = cb.greaterThanOrEqualTo(sc1.<Date>get("departureTime"), date);
            Predicate predicate2 = cb.lessThanOrEqualTo(sc1.<Date>get("departureTime"), nextDay);
            Predicate predicate3 = cb.equal(tr1.get("trainName"),trainName);
            Predicate predicate4 = cb.equal(rt1.get("orderStation"),1);
            subQuery.select(sc1).where(cb.and(predicate1, predicate2, predicate3, predicate4));
            Query<ScheduleEntity> q = getSession().createQuery(subQuery);
            ScheduleEntity schedule = q.uniqueResult();
            logger.info("schedule "+ schedule);
            if (schedule==null) {
                return null;
            }
            Integer scheduleIdFrom = schedule.getScheduleId();
            Integer scheduleIdTo = schedule.getScheduleIdLast();

            CriteriaQuery<RouteModel> query = cb.createQuery(RouteModel.class);
            Root<ScheduleEntity> sc2 = query.from(ScheduleEntity.class);
            Join<ScheduleEntity, RouteEntity> rt2 = sc2.join("routeByRouteId");
            Join<RouteEntity, StationEntity> st2 = rt2.join("stationByStationId");
            Join<RouteEntity, TrainEntity> tr2 = rt2.join("trainByTrainId");
            query.multiselect(sc2.get("scheduleId"), tr2.get("trainName"), rt2.get("orderStation"),st2.get("stationName"),
                    sc2.get("arrivalTime"), sc2.get("departureTime"));
            query.where(cb.between(sc2.<Integer>get("scheduleId"),scheduleIdFrom, scheduleIdTo))
                    .orderBy(cb.asc(rt2.get("orderStation")));
            TypedQuery<RouteModel> q2 = getSession().createQuery(query);
            List<RouteModel> result = q2.getResultList();
            for (RouteModel r : result) {
                logger.info("Schedule list: " + r);
            }
            return result;
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
            return null;
        }
    }

    @Override                          /*+-*/
    public List<ScheduleEntity> findScheduleByTrain(TrainEntity train) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<ScheduleEntity> query = cb.createQuery(ScheduleEntity.class);
            Root<ScheduleEntity> sc = query.from(ScheduleEntity.class);
            Join<ScheduleEntity, RouteEntity> rt = sc.join("routeByRouteId");
            Join<RouteEntity, TrainEntity> tr = rt.join("trainByTrainId");
            query.select(sc).where(cb.equal(tr.get("trainName"), train.getTrainName())).orderBy(cb.asc(sc.get("scheduleId")));
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
    public void addListSchedules(List<ScheduleEntity> listSchedules) {
        try {
            for (ScheduleEntity sc : listSchedules) {
                getSession().save(sc);
                logger.info("Schedule successfully added, Schedule: " + sc);
            }
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
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
    public void updateSchedule(ScheduleEntity schedule) {
        try {
            getSession().update(schedule);
            logger.info("Schedule successfully update, Schedule: " + schedule);
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
                logger.info("Free seats successfully update, FreeSeats: " + s.getFreeSeats());
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

    @Override
    public List<ScheduleEntity> findScheduleByListId(List<Integer> listSchedulesId) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<ScheduleEntity> query = cb.createQuery(ScheduleEntity.class);
            Root<ScheduleEntity> sc = query.from(ScheduleEntity.class);
            query.select(sc).where(sc.get("scheduleId").in(listSchedulesId));
            TypedQuery<ScheduleEntity> q = getSession().createQuery(query);
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

    @Override                       /*+*/
    public List<Integer> findSchedulesIdByScheduleId(int scheduleId) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();

            CriteriaQuery<ScheduleEntity> subQuery1 = cb.createQuery(ScheduleEntity.class);
            Root<ScheduleEntity> sc1 = subQuery1.from(ScheduleEntity.class);
            subQuery1.select(sc1).where(cb.equal(sc1.get("scheduleId"),scheduleId));
            Query<ScheduleEntity> q1 = getSession().createQuery(subQuery1);
            ScheduleEntity schedule = q1.uniqueResult();
            if (schedule == null) {
                return null;
            }
            String trainName = schedule.getRouteByRouteId().getTrainByTrainId().getTrainName();
            Date departureTime = schedule.getDepartureTime();

            CriteriaQuery<ScheduleEntity> query = cb.createQuery(ScheduleEntity.class);
            Root<ScheduleEntity> sc2 = query.from(ScheduleEntity.class);
            Join<ScheduleEntity, RouteEntity> rt2 = sc2.join("routeByRouteId");
            Join<RouteEntity, TrainEntity> tr2 = rt2.join("trainByTrainId");
            query.select(sc2).where(cb.and(
                    cb.equal(tr2.get("trainName"), trainName),
                    cb.equal(rt2.get("orderStation"),1),
                    cb.greaterThanOrEqualTo(sc2.<Integer>get("scheduleIdLast"), scheduleId),
                    cb.lessThanOrEqualTo(sc2.<Date>get("departureTime"), departureTime)));
            Query<ScheduleEntity> q2 = getSession().createQuery(query);
            ScheduleEntity schedule2 = q2.uniqueResult();
            if (schedule2 == null) {
                return null;
            }
            List<Integer> result = new ArrayList<>();
            for (int i = schedule2.getScheduleId(); i <= schedule2.getScheduleIdLast(); i++) {
                result.add(i);
                logger.info("Schedule id: " + i);
            }
            return result;
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
            return null;
        }
    }

    @Override
    public ScheduleEntity findScheduleByRouteIdAndDate(RouteEntity route, Date date) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<ScheduleEntity> query = cb.createQuery(ScheduleEntity.class);
            Root<ScheduleEntity> sc = query.from(ScheduleEntity.class);
            query.select(sc).where(cb.and(cb.equal(sc.get("routeByRouteId"), route)),
                                            cb.equal(sc.get("departureTime"),date));
            Query<ScheduleEntity> q = getSession().createQuery(query);
            ScheduleEntity result = q.uniqueResult();
            logger.info("Schedule: " + result);
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
