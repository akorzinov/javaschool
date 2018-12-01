package com.korzinov.services;

import com.korzinov.dao.StationDao;
import com.korzinov.entities.StationEntity;
import com.korzinov.models.StationModel;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StationServiceImpl implements StationService {

    @Autowired
    private StationDao stationDao;

    @Override
    public List<StationModel> findByNameStation(String stationName) {
        List<StationEntity> listStation = stationDao.findByNameStation(stationName);
        List<StationModel> stations = new ArrayList<>();
        for (int i = 0; i < listStation.size(); i++) {
            stations.add(i, new StationModel(listStation.get(i)));
        }
        return stations;
    }

    @Override
    public void addStation(StationModel station) {
        StationEntity newStation = new StationEntity();
        newStation.setStationName(station.getStationName());
        stationDao.addStation(newStation);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Station " + station.getStationName() + " Successfully added"));
    }

    @Override
    public void updateStation(RowEditEvent event) {
        StationModel station = (StationModel)event.getObject();
        StationEntity st = new StationEntity();
        st.setStationName(station.getStationName());
        st.setStationId(station.getStationId());
        stationDao.updateStation(st);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Station " + st.getStationName() + " Updated"));
    }

    @Override
    public void deleteStation(StationModel station) {
        StationEntity st = new StationEntity();
        st.setStationName(station.getStationName());
        st.setStationId(station.getStationId());
        stationDao.deleteStation(st);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Station " + st.getStationName() + " Deleted"));
    }

    @Override
    public int findIdByStationName(String stName) {
        return stationDao.findIdByStationName(stName);
    }

    public StationDao getStationDao() {
        return stationDao;
    }

    public void setStationDao(StationDao stationDao) {
        this.stationDao = stationDao;
    }

}
