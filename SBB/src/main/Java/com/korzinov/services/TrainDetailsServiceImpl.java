package com.korzinov.services;

import com.korzinov.dao.ScheduleDao;
import com.korzinov.dao.TicketDao;
import com.korzinov.models.FindTrain;
import com.korzinov.models.TicketTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        if (trainName.equals("")) {
            return null;
        }
        List<FindTrain> trains = scheduleDao.findTrainDetails(trainName);
        if (trains.size() % 2 == 1) {
            return null;
        }
        for (int i = 0; i < trains.size(); i++) {
            trains.get(i).setStationDest(trains.get(i + 1).getStationName());
            trains.remove(i + 1);
        }
        return trains;
    }

    @Override
    public List<FindTrain> findTrainDetailsAll() {
        List<FindTrain> trains = scheduleDao.findTrainDetailsAll();
        for (int i = 0; i < trains.size(); i++) {
            trains.add(i, findTrainDetailsUnique(trains.get(i).getTrainName()));
            trains.remove(i + 1);
        }
        return trains;
    }

    @Override
    public FindTrain findTrainDetailsUnique(String trainName) {
        List<FindTrain> trains = scheduleDao.findTrainDetailsUnique(trainName);
        FindTrain result = new FindTrain();
        for (int i = 0; i < trains.size(); i++) {
            trains.get(i).setStationDest(trains.get(i + 1).getStationName());
            trains.remove(i + 1);
            result = trains.get(i);
        }
        return result;
    }

    @Override
    public List<TicketTableModel> findPassengersByTrain(String trainName) {
        return ticketDao.findPassengersByTrain(trainName);
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

