package com.korzinov.dao;

import com.korzinov.entities.UserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public void createUser(UserEntity user) {
        try {
            getSession().save(user);
            logger.info("User successfully created, User: " + user);
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
        }

    }

    @Override
    public UserEntity findByUserName(String username) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
            Root<UserEntity> root = query.from(UserEntity.class);
            query.select(root).where(cb.equal(root.get("userName"), username));
            Query<UserEntity> q = getSession().createQuery(query);
            UserEntity user = q.uniqueResult();
            logger.info("Found user: " + user);
            return user;
        } catch (HibernateException e) {
            logger.error("Hibernate exception " + e.getMessage());
            return null;
        }
    }

    @Override
    public UserEntity findByEmail(String email) {
        try {
            CriteriaBuilder cb = getSession().getCriteriaBuilder();
            CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
            Root<UserEntity> root = query.from(UserEntity.class);
            query.select(root).where(cb.equal(root.get("email"), email));
            Query<UserEntity> q = getSession().createQuery(query);
            UserEntity user = q.uniqueResult();
            logger.info("Found user: " + user);
            return user;
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
