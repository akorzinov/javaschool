package com.korzinov.dao;

import com.korzinov.entities.ScheduleEntity;
import com.korzinov.entities.StationEntity;
import com.korzinov.entities.TrainEntity;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<ScheduleEntity> findTrainsForUser(String depStation, String destStation) {


//        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
//        Root<ScheduleEntity> scRoot = query.from(ScheduleEntity.class);
//        Root<TrainEntity> trRoot = query.from(TrainEntity.class);
//        Root<StationEntity> stRoot = query.from(StationEntity.class);
//        query.multiselect(scRoot,trRoot,stRoot);
//        query.where(cb.in(stRoot.in(depStation,destStation))).where(cb.equal(trRoot.get("trainName"),depStation));
//        Query<Object[]> q = getSession().createQuery(query);
//        List<Object[]> result = q.getResultList();
//
//        for (Object[] objects : result) {
//            ScheduleEntity sc=(ScheduleEntity) objects[0];
//            TrainEntity tr=(TrainEntity)objects[1];
//            StationEntity st=(StationEntity)objects[2];
//            System.out.println("result: " + sc.toString() + "result2: " + tr.toString() + "result3: " + st.toString());
//        }
//        System.out.println("Done");
//        List<String> listSt = new ArrayList<>();
//        listSt.add(depStation);
//        listSt.add(destStation);
//        CriteriaQuery<ScheduleEntity> query = cb.createQuery(ScheduleEntity.class);
//        Root<ScheduleEntity> scRoot = query.from(ScheduleEntity.class);
//        Root<TrainEntity> trRoot = query.from(TrainEntity.class);
//        Root<StationEntity> stRoot = query.from(StationEntity.class);

//        EntityType<ScheduleEntity> schedule = scRoot.getModel();
//        EntityType<TrainEntity> train = trRoot.getModel();
//        EntityType<StationEntity> station = stRoot.getModel();
//        Join<ScheduleEntity, TrainEntity> joinTrainName = scRoot.join("trainByTrainId");
////        Join<ScheduleEntity, StationEntity> joinStationName = scRoot.join("stationByStationId");
//        query.select(/*joinTrainName.get("trainName"), joinStationName.get("stationName"), scRoot.get("departureTime"),
//                scRoot.get("freeSeats"),scRoot.get("orderStation")*/scRoot)
//                .where(             cb.equal(joinTrainName.get("trainName"),depStation));
//                        cb.and(
//                                cb.in(joinTrainName.get("trainName")).value(depStation).value(destStation),
//                                joinTrainName.get("trainName").in(depStation,destStation),
//                                joinTrainName.get("trainName").in(listSt),
//                                cb.in(joinTrainName.get("trainName").in(listSt)),
//                                cb.equal(joinTrainName.get("trainName"),depStation)
//                        ));
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<ScheduleEntity> query = cb.createQuery(ScheduleEntity.class);
        Root<ScheduleEntity> schedule = query.from(ScheduleEntity.class);
        Join<ScheduleEntity, TrainEntity> train = schedule.join("trainByTrainId");
        query.select(schedule);
        query.where(cb.equal(train.get("trainName"),depStation));
        TypedQuery<ScheduleEntity> q = getSession().createQuery(query);
        List<ScheduleEntity> result = q.getResultList();
        System.out.println("result: " + result.toString());

        return null;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
