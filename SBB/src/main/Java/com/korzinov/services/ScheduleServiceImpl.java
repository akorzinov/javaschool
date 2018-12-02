package com.korzinov.services;

import com.korzinov.dao.RouteDao;
import com.korzinov.dao.ScheduleDao;
import com.korzinov.dao.StationDao;
import com.korzinov.dao.TrainDao;
import com.korzinov.entities.RouteEntity;
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
import java.util.Collections;
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

    @Autowired
    private RouteDao routeDao;

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
                result.get(i).setScheduleIdDep(result.get(i+1).getScheduleIdDep());
                result.remove(i + 1);
            }
        }
        Collections.sort(result);
        return result;
    }

    @Override
    public List<FindTrain> findScheduleByStation(String station) {
        return scheduleDao.findScheduleByStation(station);
    }

    @Override
    public List<RouteModel> findSchedule(String trainName, Date date) {
        return scheduleDao.findSchedule(trainName, date);
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

    @Override
    public List<RouteModel> addSchedule(List<RouteModel> listSchedules) {
        List<ScheduleEntity> listScheduleEntities = convertListSchedules(listSchedules);
        scheduleDao.addListSchedules(listScheduleEntities);
        RouteEntity route = new RouteEntity();
        Date date = new Date();
        if (!listScheduleEntities.isEmpty()) {
                route = listScheduleEntities.get(0).getRouteByRouteId();
                date = listScheduleEntities.get(0).getDepartureTime();
        }
        ScheduleEntity scheduleWithScheduleIdLast = scheduleDao.findScheduleByRouteIdAndDate(route, date);
        scheduleWithScheduleIdLast.setScheduleIdLast(scheduleWithScheduleIdLast.getScheduleId() + listScheduleEntities.size()-1);
        scheduleDao.updateSchedule(scheduleWithScheduleIdLast);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Route Successfully added"));
        return scheduleDao.findSchedule(route.getTrainByTrainId().getTrainName(),date);
    }

    @Override
    public void updateSchedule(RowEditEvent event) {
        RouteModel rm = (RouteModel)event.getObject();
        ScheduleEntity sc = convertRouteModel(rm);
        scheduleDao.updateSchedule(sc);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Schedule Updated"));
    }

    @Override
    public void deleteSchedule(RouteModel rm, List<RouteModel> listRm) {
        List<ScheduleEntity> listSchedules = new ArrayList<>();
        int removeIndex = listRm.indexOf(rm);
        if (removeIndex == 1) {
            removeIndex = 0;
        }
        for (int i = removeIndex; i < listRm.size(); i++) {
            ScheduleEntity removeSchedule = convertRouteModel(listRm.get(i));
            listSchedules.add(removeSchedule);
            listRm.remove(i);
            i--;
        }
        scheduleDao.deleteSchedules(listSchedules);
        if (!listRm.isEmpty()) {
            ScheduleEntity sc = convertRouteModel(listRm.get(0));
            sc.setScheduleIdLast(sc.getScheduleId() + listRm.size() - 1);
            scheduleDao.updateSchedule(sc);
        }
        if (listSchedules.size() > 1) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Schedules Deleted"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Schedule Deleted"));
        }
    }

    @Override
    public List<ScheduleEntity> convertListSchedules(List<RouteModel> listSchedules) {
        List<ScheduleEntity> listForDB = new ArrayList<>();
        List<Integer> listId = new ArrayList<>();
        for (RouteModel listSchedule : listSchedules) {
            listId.add(listSchedule.getRouteId());
        }
        List<RouteEntity> listRoutes = routeDao.findRouteByListId(listId);
        for (int i = 0; i < listSchedules.size(); i++) {
            ScheduleEntity newSchedule = new ScheduleEntity();
            newSchedule.setRouteByRouteId(listRoutes.get(i));
            newSchedule.setDepartureTime(listSchedules.get(i).getDepartureTime());
            newSchedule.setArrivalTime(listSchedules.get(i).getArrivalTime());
            newSchedule.setFreeSeats(listRoutes.get(i).getTrainByTrainId().getQuantitySeats());
            listForDB.add(newSchedule);
        }
        return listForDB;
    }


    @Override
    public ScheduleEntity convertRouteModel(RouteModel route) {
        ScheduleEntity sc = new ScheduleEntity();
        sc.setScheduleId(route.getScheduleId());
        sc.setFreeSeats(route.getFreeSeats());
        sc.setArrivalTime(route.getArrivalTime());
        sc.setDepartureTime(route.getDepartureTime());
        sc.setRouteByRouteId(route.getRouteByRouteId());
        sc.setScheduleIdLast(route.getScheduleIdLast());
        return sc;
    }

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

    public RouteDao getRouteDao() {
        return routeDao;
    }

    public void setRouteDao(RouteDao routeDao) {
        this.routeDao = routeDao;
    }
}
