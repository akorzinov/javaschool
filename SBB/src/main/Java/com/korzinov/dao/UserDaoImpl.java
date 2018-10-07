package com.korzinov.dao;

import com.korzinov.entities.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    @Override
    public List<UserEntity> listUser(){
        return getSession().createQuery("select u from UserEntity u").list();
    }

    @Override
    public void createUser(UserEntity user) {
        getSession().persist(user);
//                getSession().save(user);
    }
}
