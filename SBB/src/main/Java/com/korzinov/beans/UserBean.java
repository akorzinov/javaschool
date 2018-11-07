package com.korzinov.beans;


import com.korzinov.entities.UserEntity;

import javax.inject.Named;


@Named(value = "userBean")
public class UserBean {

    private String confirmPassword;
    private UserEntity user = new UserEntity();

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
