package com.korzinov.dao;

import com.korzinov.entities.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Override
    public void createUser(UserEntity user) {
//            getSession().persist(user);
            getSession().save(user);

    }

    @Override
    public UserEntity findByUserName(String username) {
//        -----------------------------1-----------------------------
        List<UserEntity> users = new ArrayList<UserEntity>();

        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<UserEntity> q = cb.createQuery(UserEntity.class);
        Root<UserEntity> c = q.from(UserEntity.class);
        ParameterExpression<String> p = cb.parameter(String.class);
        q.select(c).where(cb.equal(c.get("userName"),p));

        TypedQuery<UserEntity> query = getSession().createQuery(q);
        query.setParameter(p, username);
        users = query.getResultList();
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

        if (users.size() > 0) {
            return users.get(0);
        } else
        return null;
    }
}
