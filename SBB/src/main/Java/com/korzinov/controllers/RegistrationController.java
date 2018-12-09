package com.korzinov.controllers;

import com.korzinov.beans.UserBean;
import com.korzinov.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "registrationController")
@SessionScoped
@Controller
public class RegistrationController implements Serializable {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private UserBean userBean;

    public String createUser() throws Exception {
        if (userRegistrationService.validateUser(userBean.getUser(), userBean.getConfirmPassword())) {
            userRegistrationService.createUser(userBean.getUser());
            return "login";
        } else return null;
    }

    public UserRegistrationService getUserRegistrationService() {
        return userRegistrationService;
    }

    public void setUserRegistrationService(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
