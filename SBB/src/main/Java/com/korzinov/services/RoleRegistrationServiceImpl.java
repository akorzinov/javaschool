package com.korzinov.services;

import com.korzinov.dao.RoleDao;
import com.korzinov.entities.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleRegistrationServiceImpl implements RoleRegistrationService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public void createRole(RoleEntity userRole) {
        roleDao.createRole(userRole);
    }
}
