package com.korzinov.services;

import com.korzinov.dao.ScheduleDao;
import com.korzinov.dao.StationDao;
import com.korzinov.entities.ScheduleEntity;
import com.korzinov.models.FindTrain;
import com.korzinov.models.TrainInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BoardStationServiceImpl implements BoardStationService {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private StationDao stationDao;

    @Override
    public List<TrainInfoModel> listTrainsToBoard(String stationName, Date date) {
        List<ScheduleEntity> listSchedules = scheduleDao.findScheduleByStationAndDate(stationName, date);
        List<FindTrain> listFoundTrains;
        List<TrainInfoModel> listTrains = new ArrayList<>();
        for (int i = 0; i < listSchedules.size(); i++) {
            listFoundTrains = scheduleDao.findSchedulesIdBySchedule(listSchedules.get(i));
            for (int j = 0; j < listFoundTrains.size(); j++) {
                listFoundTrains.get(j).setStationDest(listFoundTrains.get(j+1).getStationName());
                listFoundTrains.remove(j + 1);
                listTrains.add(new TrainInfoModel(listFoundTrains.get(j)));
            }
            listTrains.get(i).setArrivalTime(listSchedules.get(i).getArrivalTime());
            listTrains.get(i).setDepartureTime(listSchedules.get(i).getDepartureTime());
        }
        Collections.sort(listTrains);
        return listTrains;
    }

    @Override
    public List<String> listStations() {
        return stationDao.listStations();
    }

    public ScheduleDao getScheduleDao() {
        return scheduleDao;
    }

    public void setScheduleDao(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    public StationDao getStationDao() {
        return stationDao;
    }

    public void setStationDao(StationDao stationDao) {
        this.stationDao = stationDao;
    }
}
