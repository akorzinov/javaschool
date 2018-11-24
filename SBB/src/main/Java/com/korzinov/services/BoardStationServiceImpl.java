package com.korzinov.services;

import com.korzinov.dao.ScheduleDao;
import com.korzinov.models.FindTrain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BoardStationServiceImpl implements BoardStationService {

    @Autowired
    private ScheduleDao scheduleDao;

    @Override
    public List<FindTrain> listTrainsToBoard() {
        List<FindTrain> listSchedule = scheduleDao.findScheduleByStationAndDate("Voronezh", new Date(System.currentTimeMillis()));
        for (int i = 0; i < listSchedule.size(); i++) {
            FindTrain trainDetails = findTrainDetailsUnique(listSchedule.get(i).getTrainName());
            listSchedule.get(i).setStationName(trainDetails.getStationName());
            listSchedule.get(i).setStationDest(trainDetails.getStationDest());
        }
        return listSchedule;
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

    public ScheduleDao getScheduleDao() {
        return scheduleDao;
    }

    public void setScheduleDao(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }
}
