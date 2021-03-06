package com.korzinov.beans;

import com.korzinov.models.UserModel;
import org.springframework.context.annotation.Scope;
import javax.inject.Named;


@Named(value = "userBean")
@Scope("session")
public class UserBean {

    private String confirmPassword;
    private UserModel user = new UserModel();

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
