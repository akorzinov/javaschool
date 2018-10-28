package com.korzinov.services;

import com.korzinov.dao.TicketDao;
import com.korzinov.dao.UserDao;
import com.korzinov.entities.TicketEntity;
import com.korzinov.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        UserEntity user = userDao.findByUserName(userName);
        return user;
    }

    @Override
    public List<TicketEntity> listTickets() {
        return ticketDao.listTickets(getUser());
    }

    @Override
    public void buyTickets(List<TicketEntity> tickets) {
        ticketDao.buyTickets(tickets);
    }

    public TicketDao getTicketDao() {
        return ticketDao;
    }

    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
