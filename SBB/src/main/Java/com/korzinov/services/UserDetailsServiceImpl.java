package com.korzinov.services;

import com.korzinov.dao.UserDao;
import com.korzinov.entities.RoleEntity;
import com.korzinov.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userDetailsService")
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity user = userDao.findByUserName(username);
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (RoleEntity role : user.getRoles()) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
//        }
//        return new User(user.getUserName(), user.getPassword(), grantedAuthorities);
//    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Inside loadUserByUsername");
        UserEntity user = userDao.findByUserName(username);
        System.out.println(user);
        System.out.println("after findByUserName");
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(UserEntity user, List<GrantedAuthority> authorities) {
        return new User(user.getUserName(),user.getPassword(),user.getEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<RoleEntity> usersRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<>();
        for (RoleEntity role: usersRoles) {
            setAuths.add(new SimpleGrantedAuthority(role.getRole()));
        }
        List<GrantedAuthority> result = new ArrayList<>(setAuths);
        return result;
    }

    public UserDao getUserDao() {
        return userDao;
    }
//
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
