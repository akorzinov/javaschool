package com.korzinov.controllers;

import com.korzinov.bo.UserBo;
import com.korzinov.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean(name="RegistrationController")
@SessionScoped
@Controller
public class RegistrationController {
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private List<UserEntity> listUsers;

    private UserBo userBo;

    public UserBo getUserBo() {
        return userBo;
    }

    @Autowired
    public void setUserBo(UserBo userBo) {
        this.userBo = userBo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String createUser() {
        UserEntity u = new UserEntity();
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setEmail(email);
        u.setUserName(userName);
        u.setPassword(password);
        u.setEnabled((byte)1);
//        u.setUserId(3);
        try {
            userBo.createUser(u);
        } catch (DataIntegrityViolationException e) {
            createMessage("User name is already used",FacesMessage.SEVERITY_ERROR);
            return null;
        }
        listUsers.add(u);
        createMessage("Registration successful", FacesMessage.SEVERITY_INFO);
        return "welcome?faces-redirect=true"; }

    public void createMessage(String msg, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(msg);
        message.setSeverity(severity);
        FacesContext.getCurrentInstance().addMessage(null,message);
    }
}
