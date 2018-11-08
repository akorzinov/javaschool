package com.korzinov.services;

import com.korzinov.beans.ScheduleBean;
import com.korzinov.dao.ScheduleDao;
import com.korzinov.dao.StationDao;
import com.korzinov.dao.TrainDao;
import com.korzinov.models.FindTrain;
import com.korzinov.models.RouteModel;
import com.korzinov.entities.ScheduleEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    private ScheduleBean scheduleBean;

    @Override
    public void findTrainsForUser() {

        List<FindTrain> result = scheduleDao.findTrainsForUser(scheduleBean.getDepStation(),scheduleBean.getDestStation(),
                scheduleBean.getDate());

//        if (result.isEmpty()) return result;

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
        String depSt = stationDao.findByNameStation(scheduleBean.getDepStation()).get(0).getStationName();
        String destSt = stationDao.findByNameStation(scheduleBean.getDestStation()).get(0).getStationName();

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

        scheduleBean.setListFoundTrains(result);
    }

    @Override
    public void findScheduleByStation() {
        scheduleBean.setListSchedule(scheduleDao.findScheduleByStation(scheduleBean.getStation()));
    }

    @Override
    public void findRoute() {
        scheduleBean.setListRoute(scheduleDao.findRoute(scheduleBean.getTrainName()));
        scheduleBean.setListTrain(trainDao.findByNameTrain(scheduleBean.getTrainName()));
    }

    @Override
    public void addRoute() {
        trainDao.addTrain(scheduleBean.getTrain());
        scheduleBean.getSchedule().setFreeSeats(trainDao.findByNameTrain(scheduleBean.getTrain().
                                                getTrainName()).get(0).getQuantitySeats());
        scheduleBean.getSchedule().setTrainByTrainId(trainDao.findByNameTrain(scheduleBean.getTrain().getTrainName()).get(0));
        try {
            scheduleBean.getSchedule().setStationByStationId(stationDao.findByNameStation(scheduleBean.getStationName()).get(0));
        } catch (IndexOutOfBoundsException e) {
            logger.error("Entered stationName " + scheduleBean.getStationName() + " doesn't exist, need enter exist stationName");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Station name " + scheduleBean.getStationName() + " doesn't exist, need enter existed station name"));
            return;
        }
        scheduleDao.addRoute(scheduleBean.getSchedule());
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Route Successfully added"));
        scheduleBean.setListTrain(trainDao.findByNameTrain(scheduleBean.getTrain().getTrainName()));
        scheduleBean.setListRoute(scheduleDao.findRoute(scheduleBean.getTrain().getTrainName()));
    }

    @Override
    public void updateRoute(RowEditEvent event) {
        RouteModel rm = (RouteModel)event.getObject();
        ScheduleEntity sc = new ScheduleEntity();
        sc.setRecordId(rm.getRecordId());
        sc.setTrainByTrainId(trainDao.findByNameTrain(rm.getTrainName()).get(0));
        try {
            sc.setStationByStationId(stationDao.findByNameStation(rm.getStationName()).get(0));
        } catch (IndexOutOfBoundsException e) {
            logger.error("Entered stationName " + scheduleBean.getStationName() + " doesn't exist, need enter exist stationName");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Station name " + scheduleBean.getStationName() + " doesn't exist, need enter existed station name"));
            return;
        }
        sc.setOrderStation(rm.getOrderStation());
        sc.setFreeSeats(rm.getFreeSeats());
        sc.setArrivalTime(rm.getArrivalTime());
        sc.setDepartureTime(rm.getDepartureTime());
        scheduleDao.updateRoute(sc);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Route Updated"));
    }

    @Override
    public void deleteRoute(RouteModel rm) {
        ScheduleEntity sc = new ScheduleEntity();
        sc.setRecordId(rm.getRecordId());
        sc.setTrainByTrainId(trainDao.findByNameTrain(rm.getTrainName()).get(0));
        sc.setStationByStationId(stationDao.findByNameStation(rm.getStationName()).get(0));
        sc.setOrderStation(rm.getOrderStation());
        sc.setFreeSeats(rm.getFreeSeats());
        sc.setArrivalTime(rm.getArrivalTime());
        sc.setDepartureTime(rm.getDepartureTime());
        scheduleDao.deleteRoute(sc);
        findRoute();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Route Deleted"));
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

    public ScheduleBean getScheduleBean() {
        return scheduleBean;
    }

    public void setScheduleBean(ScheduleBean scheduleBean) {
        this.scheduleBean = scheduleBean;
    }

    public TrainDao getTrainDao() {
        return trainDao;
    }

    public void setTrainDao(TrainDao trainDao) {
        this.trainDao = trainDao;
    }
}
