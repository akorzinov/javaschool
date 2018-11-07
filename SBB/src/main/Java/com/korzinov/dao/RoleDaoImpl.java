package com.korzinov.dao;

import com.korzinov.entities.RoleEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    static final Logger logger = LogManager.getLogger(RoleDaoImpl.class);

    @Override
    public void createRole(RoleEntity role) {
        try {
            getSession().save(role);
            logger.info("Role for user successfully added, Role: " + role);
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
