package com.korzinov.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean (name= "LoginController")
@SessionScoped
public class LoginController {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) { this.login = login; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password; }

    public String checkLogin(){
        return "welcome?faces-redirect=true";
    }

    public String registration() {
        return "registration?faces-redirect=true";
    }

}
