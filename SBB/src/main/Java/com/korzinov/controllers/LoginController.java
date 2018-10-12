package com.korzinov.controllers;

import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean (name= "LoginController")
@SessionScoped
@Controller
public class LoginController implements Serializable{
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

    public String checkLogin() throws ServletException, IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        login = context.getRequestParameterMap().get("username");
        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/login");
        dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
        FacesContext.getCurrentInstance().responseComplete();
//        String url = "/j_spring_security_check?j_username=" + "&j_password=" + password;
//        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher(url);
//        dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
//        FacesContext.getCurrentInstance().responseComplete();
        return null;
    }

    public String registration() {
        return "registration?faces-redirect=true";
    }

}
