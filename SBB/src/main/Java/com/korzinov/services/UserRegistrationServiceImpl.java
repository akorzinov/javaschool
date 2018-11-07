package com.korzinov.services;

import com.korzinov.beans.UserBean;
import com.korzinov.dao.RoleDao;
import com.korzinov.dao.UserDao;
import com.korzinov.entities.RoleEntity;
import com.korzinov.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Service
@Transactional
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserBean userBean;

    @Override
    public void createUser() {
        UserEntity newUser = new UserEntity();
        String cryptedPassword = new BCryptPasswordEncoder().encode(userBean.getUser().getPassword());
        newUser.setFirstName(userBean.getUser().getFirstName());
        newUser.setLastName(userBean.getUser().getLastName());
        newUser.setEmail(userBean.getUser().getEmail());
        newUser.setUserName(userBean.getUser().getUserName());
        newUser.setPassword(cryptedPassword);
        newUser.setBirthday(userBean.getUser().getBirthday());
        newUser.setEnabled(true);
        userDao.createUser(newUser);

        RoleEntity newRole = new RoleEntity();
        newRole.setRole("ROLE_USER");
        newRole.setUser(newUser);
        roleDao.createRole(newRole);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("User successfully created"));

    }

    @Override
    public boolean validateUser() {
        if (!(userBean.getUser().getPassword().equals(userBean.getConfirmPassword()))) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("User password do not equals confirm password"));
            return false;
        }
        return true;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
