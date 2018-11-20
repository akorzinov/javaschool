package com.korzinov.dao;

import com.korzinov.entities.TicketEntity;
import com.korzinov.entities.TrainEntity;
import com.korzinov.entities.UserEntity;
import com.korzinov.models.TicketTableModel;
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
import java.util.Date;
import java.util.List;

@Repository
public class TicketDaoImpl implements TicketDao{

    static final Logger logger = LogManager.getLogger(StationDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TicketEntity> listTickets(UserEntity user) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<TicketEntity> query = cb.createQuery(TicketEntity.class);
            Root<TicketEntity> t = query.from(TicketEntity.class);
            query.select(t).where(cb.equal(t.get("userByUserId"), user));
            Query<TicketEntity> q = getSession().createQuery(query);
            List<TicketEntity> listTickets = q.getResultList();
            for (TicketEntity r : listTickets) {
                logger.info("Tickets: " + r);
            }
            return listTickets;
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
            return null;
        }
    }

    @Override
    public void buyTickets(List<TicketEntity> tickets) {
        try {
            for (TicketEntity t : tickets) {
                getSession().save(t);
                logger.info("Ticket successfully saved, Ticket: " + t);
            }
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
            return;
        }
    }

    @Override
    public boolean checkSamePass(TrainEntity train, String firstName, String lastName, Date birthday) {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<TicketEntity> query = cb.createQuery(TicketEntity.class);
        Root<TicketEntity> t = query.from(TicketEntity.class);
        query.select(t).where(cb.and(
                cb.equal(t.get("trainByTrainId"), train),
                cb.equal(t.get("firstName"), firstName),
                cb.equal(t.get("lastName"), lastName),
                cb.equal(t.get("birthday"),birthday)));
        Query<TicketEntity> q = getSession().createQuery(query);
        List<TicketEntity> result = q.getResultList();
        if (result.isEmpty()) {
            return false;
        } else return true;
    }

    @Override
    public List<TicketTableModel> findPassengersByTrain(String trainName) {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<TicketTableModel> query = cb.createQuery(TicketTableModel.class);
        Root<TicketEntity> tk = query.from(TicketEntity.class);
        Join<TrainEntity, TrainEntity> tr = tk.join("trainByTrainId");
        query.multiselect(tk.get("ticketId"), tk.get("firstName"), tk.get("lastName"), tk.get("birthday")).
                where(cb.equal(tr.get("trainName"), trainName));
        Query<TicketTableModel> q = getSession().createQuery(query);
        List<TicketTableModel> result = q.getResultList();
        return result;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
