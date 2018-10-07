package com.korzinov.dao;

import com.korzinov.entities.UserEntity;

import java.util.List;

public interface UserDao {

    void createUser(UserEntity user);

    List<UserEntity> listUser();
}
