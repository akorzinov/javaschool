package com.korzinov.dao;

import com.korzinov.entities.StationEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class StationDaoImpl implements StationDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public StationEntity findByNameStation(String nameStation) {

        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<StationEntity> query = cb.createQuery(StationEntity.class);
        Root<StationEntity> root = query.from(StationEntity.class);
        query.select(root).where(cb.equal(root.get("stationName"),nameStation));
        Query<StationEntity> q = getSession().createQuery(query);
        StationEntity station = q.getSingleResult();
        return station;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
