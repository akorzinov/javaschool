package com.korzinov.services;

import com.korzinov.dao.ScheduleDao;
import com.korzinov.dao.StationDao;
import com.korzinov.dao.TrainDao;
import com.korzinov.entities.StationEntity;
import com.korzinov.entities.TrainEntity;
import com.korzinov.models.*;
import com.korzinov.entities.ScheduleEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService{

    static final Logger logger = LogManager.getLogger(ScheduleServiceImpl.class);

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private StationDao stationDao;

    @Autowired
    private TrainDao trainDao;

    @Override
    public List<FindTrain> findTrainsForUser(String depStation, String destStation, Date date) {

        List<FindTrain> result = scheduleDao.findTrainsForUser(depStation, destStation, date);
        if (result.isEmpty()) return null;
                    /*удаление одиночных записей и удаление записей неправильного направления*/
        for (int i = 0; i < result.size(); i++) {
            if (result.size() == 1) {
                result.clear();
                return null;
            }
            if (i == result.size() - 1) {
                result.remove(i);
                continue;
            }
            if (!result.get(i).getTrainName().equals(result.get(i+1).getTrainName())) {
                result.remove(i);
                i--;
                continue;
            }
            while (result.get(i).getTrainName().equals(result.get(i+1).getTrainName())) {
                if (result.get(i).getStationName().equals(destStation)) {
                    result.remove(i);
                    if (i >= result.size()-1) {
                        break;
                    }
                    continue;
                }
                if (result.get(i).getStationName().equals(depStation) &&
                        result.get(i).getOrderStation() < result.get(i+1).getOrderStation()) {
                    i++;
                } else if (result.get(i).getStationName().equals(destStation) &&
                        result.get(i).getOrderStation() > result.get(i+1).getOrderStation()){
                    i++;
                } else {
                    result.remove(i);
                    result.remove(i);
                    i--;
                }
                i++;
                if (i >= result.size()-1) {
                    break;
                }
            }
            i--;
        }
                    /*переправка списка в 1 строку на 1 поезд*/
        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i).getStationName().equals(depStation)) {
                result.get(i).setStationDest(destStation);
                result.get(i).setArrivalTime(result.get(i + 1).getArrivalTime());
                result.remove(i + 1);
            } else {
                result.get(i).setStationName(depStation);
                result.get(i).setStationDest(destStation);
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
    public List<TrainModel> findRouteTrain(String trainName) {
        List<TrainEntity> listTrain = trainDao.findByNameTrain(trainName);
        List<TrainModel> trains = new ArrayList<>();
        for (int i = 0; i < listTrain.size(); i++) {
            trains.add(i, new TrainModel(listTrain.get(i)));
        }
        return trains;
    }

//    @Override
//    public void addRoute(TrainModel train, ScheduleModel schedule, String stationName) {
//        trainDao.addTrain(convertTrainModel(train));
//        schedule.setFreeSeats(trainDao.findByNameTrainUnique(train.getTrainName()).getQuantitySeats());
//        StationEntity st = stationDao.findByNameStationUnique(stationName);
//        if (st != null) {
//            scheduleDao.addRoute(convertScheduleModel(schedule, train.getTrainName(), st));
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage("Route Successfully added"));
//        } else {
//            logger.error("Entered stationName " + stationName + " doesn't exist, need enter exist stationName");
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage("Station name " + stationName + " doesn't exist, need enter existed station name"));
//        }
//    }

//    @Override
//    public void updateRoute(RowEditEvent event) {
//        RouteModel rm = (RouteModel)event.getObject();
//        ScheduleEntity sc = new ScheduleEntity();
//        sc.setRecordId(rm.getRecordId());
//        sc.setTrainByTrainId(trainDao.findByNameTrainUnique(rm.getTrainName()));
//        StationEntity st = stationDao.findByNameStationUnique(rm.getStationName());
//        if (st != null) {
//            sc.setStationByStationId(st);
//        } else {
//            logger.error("Entered stationName " + rm.getStationName() + " doesn't exist, need enter exist stationName");
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage("Station name " + rm.getStationName() + " doesn't exist, need enter existed station name"));
//            return;
//        }
//        sc.setOrderStation(rm.getOrderStation());
//        sc.setFreeSeats(rm.getFreeSeats());
//        sc.setArrivalTime(rm.getArrivalTime());
//        sc.setDepartureTime(rm.getDepartureTime());
//        scheduleDao.updateRoute(sc);
//        FacesContext.getCurrentInstance().addMessage(null,
//                new FacesMessage("Route Updated"));
//    }

//    @Override
//    public void deleteRoute(RouteModel rm) {
//        ScheduleEntity sc = new ScheduleEntity();
//        sc.setRecordId(rm.getRecordId());
//        sc.setTrainByTrainId(trainDao.findByNameTrainUnique(rm.getTrainName()));
//        sc.setStationByStationId(stationDao.findByNameStationUnique(rm.getStationName()));
//        sc.setOrderStation(rm.getOrderStation());
//        sc.setFreeSeats(rm.getFreeSeats());
//        sc.setArrivalTime(rm.getArrivalTime());
//        sc.setDepartureTime(rm.getDepartureTime());
//        scheduleDao.deleteRoute(sc);
//        FacesContext.getCurrentInstance().addMessage(null,
//                new FacesMessage("Route Deleted"));
//    }


//    @Override
//    public ScheduleEntity convertScheduleModel(ScheduleModel schedule, String trainName, StationEntity station) {
//        ScheduleEntity newSchedule = new ScheduleEntity();
//        newSchedule.setTrainByTrainId(trainDao.findByNameTrainUnique(trainName));
//        newSchedule.setStationByStationId(station);
//        newSchedule.setFreeSeats(schedule.getFreeSeats());
//        newSchedule.setOrderStation(schedule.getOrderStation());
//        newSchedule.setDepartureTime(schedule.getDepartureTime());
//        newSchedule.setArrivalTime(schedule.getArrivalTime());
//        return newSchedule;
//    }

    @Override
    public List<String> nameStationSuggestions(String stationName) {
        return stationDao.findSuggestionsStation(stationName);
    }

    @Override
    public List<String> nameTrainSuggestions(String trainName) {
        return trainDao.findSuggestionsTrain(trainName);
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

    public TrainDao getTrainDao() {
        return trainDao;
    }

    public void setTrainDao(TrainDao trainDao) {
        this.trainDao = trainDao;
    }
}
