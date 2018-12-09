package com.korzinov.dao;

import com.korzinov.entities.TrainEntity;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TrainDaoImpl implements TrainDao {

    static final Logger logger = LogManager.getLogger(StationDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TrainEntity> findByNameTrain(String nameTrain) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<TrainEntity> query = cb.createQuery(TrainEntity.class);
            Root<TrainEntity> root = query.from(TrainEntity.class);
            query.select(root).where(cb.equal(root.get("trainName"), nameTrain));
            Query<TrainEntity> q = getSession().createQuery(query);
            List<TrainEntity> trainList = q.getResultList();
            for (TrainEntity t : trainList) {
                logger.info("Tickets: " + t);
            }
            return trainList;
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
            return null;
        }
    }

    @Override
    public TrainEntity findByNameTrainUnique(String nameTrain) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<TrainEntity> query = cb.createQuery(TrainEntity.class);
            Root<TrainEntity> tr = query.from(TrainEntity.class);
            query.select(tr).where(cb.equal(tr.get("trainName"), nameTrain));
            Query<TrainEntity> q = getSession().createQuery(query);
            TrainEntity train = q.uniqueResult();
                logger.info("Train: " + train);
            return train;
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
            return null;
        }
    }

    @Override
    public void addTrain(TrainEntity tr) {
        if (findByNameTrain(tr.getTrainName()).isEmpty()) {
            try {
                getSession().save(tr);
                logger.info("Train successfully saved, Train: " + tr);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Train " + tr.getTrainName() + " Successfully added"));
            } catch (HibernateException e) {
                logger.error("Hibernate exception " + e.getMessage());
            }
        }   else {
            logger.info("Train already exist, trying to add route to current train");
        }
    }

    @Override
    public void updateTrain(TrainEntity tr) {
        try {
            getSession().update(tr);
            logger.info("Train successfully update, Train: " + tr);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Train " + tr.getTrainName() + " Updated"));
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
        }
    }

    @Override
    public void deleteTrain(TrainEntity tr) {
        try {
            getSession().delete(tr);
            logger.info("Train successfully delete, Train: " + tr);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Train " + tr.getTrainName() + " Deleted"));
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Train " + tr.getTrainName() + " cannot be delete, most likely train still have route"));
        }
    }

    @Override
    public List<String> findSuggestionsTrain(String trainName) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<String> query = cb.createQuery(String.class);
            Root<TrainEntity> tr = query.from(TrainEntity.class);
            query.multiselect(tr.get("trainName")).where(cb.like(tr.<String>get("trainName"), "%" + trainName + "%"));
            Query<String> q = getSession().createQuery(query);
            List<String> trains = q.getResultList();
            logger.info("Trains: " + trains);
            return trains;
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
