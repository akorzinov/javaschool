package com.korzinov.dao;

import com.korzinov.entities.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void createUser(UserEntity user) {
//            getSession().persist(user);
            getSession().save(user);
    }

    @Override
    public UserEntity findByUserName(String username) {
        List<UserEntity> users = new ArrayList<>();
        users = getSession().createQuery("from UserEntity where userName=?").setParameter(0, username).list();
        if (users.size() > 0) {
            return users.get(0);
        } else
        return null;
    }
}
