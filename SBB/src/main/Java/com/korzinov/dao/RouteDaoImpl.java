package com.korzinov.dao;

import com.korzinov.entities.RouteEntity;
import com.korzinov.entities.TrainEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

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

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
