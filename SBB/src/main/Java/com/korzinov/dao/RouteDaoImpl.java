package com.korzinov.dao;

import com.korzinov.entities.RouteEntity;
import com.korzinov.entities.StationEntity;
import com.korzinov.entities.TrainEntity;
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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RouteDaoImpl implements RouteDao {

    static final Logger logger = LogManager.getLogger(ScheduleDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Integer findQuantityRoute(String trainName) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<Integer> query = cb.createQuery(Integer.class);
            Root<RouteEntity> rt = query.from(RouteEntity.class);
            Join<RouteEntity, TrainEntity> tr = rt.join("trainByTrainId");
            query.multiselect(cb.max(rt.<Number>get("orderStation")));
            query.where(cb.equal(tr.get("trainName"),trainName));
            Query<Integer> q = getSession().createQuery(query);
            Integer result = q.uniqueResult();
            logger.info("QuantityRoute : " + result);
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
            Root<RouteEntity> rt = query.from(RouteEntity.class);
            Join<RouteEntity, StationEntity> st = rt.join("stationByStationId");
            Join<RouteEntity, TrainEntity> tr = rt.join("trainByTrainId");
            query.multiselect(rt.get("routeId"), tr.get("trainName"), rt.get("orderStation"),st.get("stationName"));
            query.where(cb.equal(tr.get("trainName"), trainName)).orderBy(cb.asc(rt.get("orderStation")));
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
    public void addRoute(RouteEntity route) {
        try {
            getSession().save(route);
            logger.info("Route successfully saved, Route: " + route);
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
        }

    }

    @Override
    public void updateRoute(RouteEntity route) {
        try {
            getSession().update(route);
            logger.info("Route successfully update, Route: " + route);
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
        }
    }

    @Override
    public void deleteRoute(RouteEntity route) {
        try {
            getSession().delete(route);
            logger.info("Route successfully delete, Route: " + route);
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
