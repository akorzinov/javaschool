package com.korzinov.controllers;

import org.springframework.stereotype.Controller;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Named(value = "loginController")
@SessionScoped
@Controller
public class LoginController implements Serializable{
    private String username;
    private String password;

    public String login() throws ServletException, IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/login");
        dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
//        FacesContext.getCurrentInstance().responseComplete();
        return null;
    }

    public String logout() throws ServletException, IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }

    public String registration() {
        return "registration?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password; }
}
