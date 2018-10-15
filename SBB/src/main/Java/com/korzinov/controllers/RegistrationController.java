package com.korzinov.controllers;

import com.korzinov.models.UserModel;
import com.korzinov.services.UserRegistrationService;
import com.korzinov.entities.UserEntity;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.support.RequestContext;

import java.util.List;

@ManagedBean(name="RegistrationController")
@SessionScoped
@Controller
public class RegistrationController {

    @Autowired
    private UserRegistrationService userCreateService;

    private String confirmPassword;
    private UserEntity user = new UserEntity();

    public String createUser() throws Exception {
        try {
            this.validateUser();
            UserEntity u = new UserEntity();
            String cryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            u.setEmail(user.getEmail());
            u.setUserName(user.getUserName());
            u.setPassword(cryptedPassword);
            u.setEnabled(true);

            userCreateService.createUser(u);
            return "login";
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void validateUser() throws Exception {
        if (!(user.getPassword().equals(getConfirmPassword()))) {
            throw new Exception("password not equals confirm password");
        }
    }

    public UserRegistrationService getUserCreateService() {
        return userCreateService;
    }

    public void setUserCreateService(UserRegistrationService userCreateService) {
        this.userCreateService = userCreateService;
    }

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

//    @Autowired
//    private UserModel userModel;
//
//    @ManagedProperty(value = "#{userRegistrationService}")
//    private UserRegistrationService userRegistrationService;
//
//    public UserRegistrationService getUserRegistrationService() {
//        return userRegistrationService;
//    }
//
//    public void setUserRegistrationService(UserRegistrationService userRegistrationService) {
//        this.userRegistrationService = userRegistrationService;
//    }
//
//    public String createUser() {
//        UserEntity u = new UserEntity();
//        String cryptedPassword = new BCryptPasswordEncoder().encode(userModel.getPassword());
//        u.setFirstName(userModel.getFirstName());
//        u.setLastName(userModel.getLastName());
//        u.setEmail(userModel.getEmail());
//        u.setUserName(userModel.getUserName());
//        u.setPassword(cryptedPassword);
//        u.setEnabled(true);
//        try {
//            userRegistrationService.createUser(u);
//        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
//            createMessage("User name or email is already used",FacesMessage.SEVERITY_ERROR);
//            return null;
//        }
//        createMessage("Registration successful", FacesMessage.SEVERITY_INFO);
//        return "welcome?faces-redirect=true"; }
//
//    public void createMessage(String msg, FacesMessage.Severity severity) {
//        FacesMessage message = new FacesMessage(msg);
//        message.setSeverity(severity);
//        FacesContext.getCurrentInstance().addMessage(null,message);
//    }
//
//    public UserModel getUserModel() {
//        return userModel;
//    }
//
//    public void setUserModel(UserModel userModel) {
//        this.userModel = userModel;
//    }
}
