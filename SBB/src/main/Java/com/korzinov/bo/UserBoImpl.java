package com.korzinov.bo;

import com.korzinov.dao.UserDao;
import com.korzinov.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userBo")
@Transactional
public class UserBoImpl implements UserBo {

    @Autowired
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createUser(UserEntity user) {
        userDao.createUser(user);
    }

    @Override
    public List<UserEntity> listUsers() {
        return userDao.listUser();
    }
}
