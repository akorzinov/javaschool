package com.korzinov.services;

import com.korzinov.dao.ScheduleDao;
import com.korzinov.dao.TicketDao;
import com.korzinov.models.FindTrain;
import com.korzinov.models.TicketTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class TrainDetailsServiceImpl implements TrainDetailsService {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private TicketDao ticketDao;

    @Override
    public List<FindTrain> findTrainDetails(String trainName) {
        List<FindTrain> trains = scheduleDao.findTrainDetails(trainName);
        for (int i = 0; i < trains.size(); i++) {
            trains.get(i).setStationDest(trains.get(i+1).getStationName());
            trains.get(i).setArrivalTime(trains.get(i+1).getArrivalTime());
            trains.remove(i + 1);
        }
            Collections.sort(trains);
        return trains;
    }

    @Override
    public List<FindTrain> findTrainDetailsAll() {
        List<FindTrain> trains = scheduleDao.findTrainDetailsAll();
        for (int i = 0; i < trains.size(); i++) {
            trains.get(i).setStationDest(trains.get(i+1).getStationName());
            trains.get(i).setArrivalTime(trains.get(i+1).getArrivalTime());
            trains.remove(i + 1);
        }
            Collections.sort(trains);
        return trains;
    }

    public List<TicketTableModel> findPassengersByScheduleId(int scheduleId, int scheduleIdLast) {
        return ticketDao.findPassengersBySchedulesId(scheduleId, scheduleIdLast);
    }

    public ScheduleDao getScheduleDao() {
        return scheduleDao;
    }

    public void setScheduleDao(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    public TicketDao getTicketDao() {
        return ticketDao;
    }

    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }
}

