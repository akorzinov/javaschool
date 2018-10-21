package com.korzinov.dao;

import com.korzinov.entities.TrainEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class TrainDaoImpl implements TrainDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public TrainEntity findByNameTrain(String nameTrain) {

        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<TrainEntity> query = cb.createQuery(TrainEntity.class);
        Root<TrainEntity> root = query.from(TrainEntity.class);
        query.select(root).where(cb.equal(root.get("trainName"),nameTrain));
        Query<TrainEntity> q = getSession().createQuery(query);
        TrainEntity train = q.getSingleResult();
        return train;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
