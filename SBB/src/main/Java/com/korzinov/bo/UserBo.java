package com.korzinov.bo;

import com.korzinov.entities.UserEntity;

import java.util.List;

public interface UserBo {

    void createUser(UserEntity user);
    List<UserEntity> listUsers();
}
