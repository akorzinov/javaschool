package com.korzinov.dao;

import com.korzinov.entities.UserEntity;

public interface UserDao {

    void createUser(UserEntity user);

    UserEntity findByUserName(String username);

}
