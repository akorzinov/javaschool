package com.korzinov.controllers;

import com.korzinov.entities.RoleEntity;
import com.korzinov.services.RoleRegistrationService;
import com.korzinov.services.UserRegistrationService;
import com.korzinov.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.io.Serializable;

@Named(value = "registrationController")
@SessionScoped
@Controller
public class RegistrationController implements Serializable {

    @Autowired
    private UserRegistrationService userCreateService;

    @Autowired
    private RoleRegistrationService roleRegistrationService;

    private String confirmPassword;
    private UserEntity user = new UserEntity();

    public String createUser() throws Exception {
        try {
            this.validateUser();
            UserEntity newUser = new UserEntity();
            String cryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setEmail(user.getEmail());
            newUser.setUserName(user.getUserName());
            newUser.setPassword(cryptedPassword);
            newUser.setBirthday(user.getBirthday());
            newUser.setEnabled(true);
            userCreateService.createUser(newUser);

            RoleEntity newRole = new RoleEntity();
            newRole.setRole("ROLE_USER");
            newRole.setUser(newUser);
            roleRegistrationService.createRole(newRole);

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

    public RoleRegistrationService getRoleRegistrationService() {
        return roleRegistrationService;
    }

    public void setRoleRegistrationService(RoleRegistrationService roleRegistrationService) {
        this.roleRegistrationService = roleRegistrationService;
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

}
