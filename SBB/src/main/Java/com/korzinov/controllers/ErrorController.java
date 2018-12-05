package com.korzinov.controllers;

import org.primefaces.context.PrimeFacesContext;
import org.springframework.stereotype.Controller;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "errorController")
@Controller
@RequestScoped
public class ErrorController {

    public String getStatusCode(){
        return String.valueOf((Integer) PrimeFacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.status_code"));
    }

    public String getMessage(){
        return (String)PrimeFacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.message");
    }

    public String getRequestURI(){
        return (String)PrimeFacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.request_uri");
    }

}
