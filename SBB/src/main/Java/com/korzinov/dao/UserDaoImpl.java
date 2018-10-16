package com.korzinov.dao;

import com.korzinov.entities.UserEntity;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createUser(UserEntity user) {
//            getSession().persist(user);
            getSession().save(user);

    }

    @Override
    public UserEntity findByUserName(String username) {
//        -----------------------------1-----------------------------
//        List<UserEntity> users = new ArrayList<UserEntity>();
        System.out.println("username is " + username);
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        query.select(root).where(cb.equal(root.get("userName"),username));
        Query<UserEntity> q = getSession().createQuery(query);
        UserEntity user = q.getSingleResult();
        System.out.println(user.toString());
        return user;

//----------------------------------2-------------------------------------
//        Criteria crit = getSession().createCriteria(UserEntity.class);
//        crit.add(Restrictions.eq("userName", username));
//        UserEntity user = (UserEntity)crit.uniqueResult();
//        if (user != null) {
//            Hibernate.initialize(user.getRoles());
//        }
//        return user;
//----------------------------------3-------------------------------------------
//        users = getSession().createQuery("from UserEntity where userName=?").setParameter(0, username).list();
//        if (users.size() > 0) {
//            return users.get(0);
//        } else
//        return null;
    }

}
