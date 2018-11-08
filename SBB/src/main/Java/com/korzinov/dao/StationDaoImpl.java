package com.korzinov.dao;

import com.korzinov.entities.StationEntity;
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
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class StationDaoImpl implements StationDao {

    static final Logger logger = LogManager.getLogger(StationDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<StationEntity> findByNameStation(String nameStation) {

        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<StationEntity> query = cb.createQuery(StationEntity.class);
            Root<StationEntity> st = query.from(StationEntity.class);
            query.select(st).where(cb.like(st.<String>get("stationName"), "%" + nameStation + "%"));
            Query<StationEntity> q = getSession().createQuery(query);
            List<StationEntity> listStation = q.getResultList();
            for (StationEntity s : listStation) {
                logger.info("Station List: " + s);
            }
            return listStation;
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
            return null;
        }

    }

    @Override
    public int findIdByStationName(String stName) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<StationEntity> query = cb.createQuery(StationEntity.class);
            Root<StationEntity> st = query.from(StationEntity.class);
            query.select(st).where(cb.equal(st.<String>get("stationName"), stName));
            Query<StationEntity> q = getSession().createQuery(query);
            StationEntity result = q.getSingleResult();
            logger.info("Station ID: " + result.getStationId());
            return result.getStationId();
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
            return 0;
        }
    }

    @Override
    public void addStation(StationEntity st) {
        try {
            getSession().save(st);
            logger.info("Station successfully saved, Station: " + st);
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
        }
    }

    @Override
    public void updateStation(StationEntity st) {
        try {
            getSession().update(st);
            logger.info("Station successfully update, Station: " + st);
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
        }
    }

    @Override
    public void deleteStation(StationEntity st) {
        try {
            getSession().delete(st);
            logger.info("Station successfully delete, Station: " + st);
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
        }
    }

    @Override
    public String findStationNameById(int id) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<StationEntity> query = cb.createQuery(StationEntity.class);
            Root<StationEntity> st = query.from(StationEntity.class);
            query.select(st).where(cb.equal(st.get("stationId"), id));
            Query<StationEntity> q = getSession().createQuery(query);
            StationEntity result = q.getSingleResult();
            logger.info("Station name: " + result.getStationName());
            return result.getStationName();
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
