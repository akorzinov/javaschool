package com.korzinov.services;

import com.korzinov.dao.ScheduleDao;
import com.korzinov.dao.StationDao;
import com.korzinov.entities.FindTrain;
import com.korzinov.entities.RouteModel;
import com.korzinov.entities.ScheduleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private StationDao stationDao;

    @Override
    public List<FindTrain> findTrainsForUser(String depStation, String destStation, Date date) {

        List<FindTrain> result = scheduleDao.findTrainsForUser(depStation,destStation,date);

        if (result.isEmpty()) return result;

                    /*удаление одиночных записей*/
        for (int i = 0; i < result.size(); i++) {
            if (result.size()==1) {
                result.remove(0);
                continue;
            }
            if (i == 2 & result.size()==3) {
                result.remove(2);
                continue;
            }
            if (!(i + 2 == result.size())) {
                if (!(result.get(i).getTrainName().equals(result.get(i + 1).getTrainName()))) {
                    result.remove(i);
                    i = i - 2;
                } else if (i + 3 == result.size()) {
                    i--;
                }
                i++;
            } else if (!(result.get(i+1).getTrainName().equals(result.get(i).getTrainName()))) {
                result.remove(i + 1);
                if (i + 1 == result.size()) {
                    result.remove(i);
                }
                if (!(i==1)){
                    i--;
                }
            } else i++;
        }

                /*вытаскивание актуальных названий станций*/
        String depSt = stationDao.findByNameStation(depStation).get(0).getStationName();
        String destSt = stationDao.findByNameStation(destStation).get(0).getStationName();

                    /*удаление записей неправильного направления*/
        for (int i = 0; i < result.size()-1 ; i+=2) {
            if (result.get(i).getStationName().equals(depSt)) {
                if (result.get(i).getOrderStation() > result.get(i + 1).getOrderStation()) {
                    result.remove(i);
                    result.remove(i);
                    i-=2;
                }
            } else {
                if (result.get(i).getOrderStation() < result.get(i + 1).getOrderStation()) {
                    result.remove(i);
                    result.remove(i);
                    i-=2;
                }
            }
        }

                    /*переправка списка в 1 строку на 1 поезд*/
        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i).getStationName().equals(depSt)) {
                result.get(i).setStationDest(destSt);
                result.get(i).setArrivalTime(result.get(i + 1).getArrivalTime());
                result.remove(i + 1);
            } else {
                result.get(i).setStationName(depSt);
                result.get(i).setStationDest(destSt);
                result.get(i).setDepartureTime(result.get(i + 1).getDepartureTime());
                result.get(i).setFreeSeats(result.get(i + 1).getFreeSeats());
                result.remove(i + 1);
            }
        }

        return result;
    }

    @Override
    public List<FindTrain> findScheduleByStation(String station) {
        return scheduleDao.findScheduleByStation(station);
    }

    @Override
    public List<RouteModel> findRoute(String trainName) {
        return scheduleDao.findRoute(trainName);
    }

    @Override
    public void addRoute(ScheduleEntity schedule) {
        scheduleDao.addRoute(schedule);
    }

    @Override
    public void updateRoute(ScheduleEntity schedule) {
        scheduleDao.updateRoute(schedule);
    }

    @Override
    public void deleteRoute(ScheduleEntity schedule) {
        scheduleDao.deleteRoute(schedule);
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
