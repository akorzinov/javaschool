package com.korzinov.services;

import com.korzinov.dao.RoleDao;
import com.korzinov.dao.UserDao;
import com.korzinov.entities.RoleEntity;
import com.korzinov.entities.UserEntity;
import com.korzinov.models.UserModel;
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

    @Override
    public void createUser(UserModel user) {
        UserEntity newUser = new UserEntity();
        String cryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setUserName(user.getUserName());
        newUser.setPassword(cryptedPassword);
        newUser.setBirthday(user.getBirthday());
        newUser.setEnabled(true);
        userDao.createUser(newUser);

        RoleEntity newRole = new RoleEntity();
        newRole.setRole("ROLE_USER");
        newRole.setUserByUserId(newUser);
        roleDao.createRole(newRole);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("User successfully created"));

    }

    @Override
    public boolean validateUser(String password, String confirmPassword) {
        if (!(password.equals(confirmPassword))) {
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
}
