package com.korzinov.services;

import com.korzinov.dao.ScheduleDaoImpl;
import com.korzinov.entities.ScheduleEntity;
import com.korzinov.models.FindTrain;
import com.korzinov.models.TrainInfoModel;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;

public class BoardStationServiceImplTest {

    @Test
    public void testListTrainsToBoard() {
        List<ScheduleEntity> scheduleList = new ArrayList<>();
        Date date = java.sql.Date.valueOf(LocalDate.now());
        for (int i = 1; i < 5; i++) {
            ScheduleEntity schedule = new ScheduleEntity();
            schedule.setArrivalTime(new Date(date.getTime() + i * 10000));
            schedule.setDepartureTime(new Date(date.getTime() + i * 10000 + 30000));
            scheduleList.add(schedule);
        }
        ScheduleDaoImpl mockito = mock(ScheduleDaoImpl.class);
        when(mockito.findScheduleByStationAndDate("Voronezh", date)).thenReturn(scheduleList);
        List<FindTrain> findTrains1 = new ArrayList<>(Arrays.asList(new FindTrain("Voronezh") , new FindTrain("Moscow")));
        List<FindTrain> findTrains2 = new ArrayList<>(Arrays.asList(new FindTrain("Saint-Petersburg") , new FindTrain("Krasnodar")));
        List<FindTrain> findTrains3 = new ArrayList<>(Arrays.asList(new FindTrain("Moscow") , new FindTrain("Rostov-on-Don")));
        List<FindTrain> findTrains4 = new ArrayList<>(Arrays.asList(new FindTrain("Moscow") , new FindTrain("Krasnodar")));
        when(mockito.findSchedulesIdBySchedule(scheduleList.get(0))).thenReturn(findTrains1);
        when(mockito.findSchedulesIdBySchedule(scheduleList.get(1))).thenReturn(findTrains2);
        when(mockito.findSchedulesIdBySchedule(scheduleList.get(2))).thenReturn(findTrains3);
        when(mockito.findSchedulesIdBySchedule(scheduleList.get(3))).thenReturn(findTrains4);

        BoardStationServiceImpl boardStationService = new BoardStationServiceImpl();
        boardStationService.setScheduleDao(mockito);
        List<TrainInfoModel> actualResult = boardStationService.listTrainsToBoard("Voronezh", date);
        List<TrainInfoModel> expectedResult = new ArrayList<>(Arrays.asList(
                new TrainInfoModel("Voronezh", "Moscow", new Date(date.getTime() + 10000), new Date(date.getTime() + 40000),"On time", "Voronezh"),
                new TrainInfoModel("Saint-Petersburg", "Krasnodar", new Date(date.getTime() + 20000), new Date(date.getTime() + 50000),"On time", "Voronezh"),
                new TrainInfoModel("Moscow", "Rostov-on-Don", new Date(date.getTime() + 30000), new Date(date.getTime() + 60000),"On time", "Voronezh"),
                new TrainInfoModel("Moscow", "Krasnodar", new Date(date.getTime() + 40000), new Date(date.getTime() + 70000),"On time", "Voronezh")));
        assertThat(actualResult, is(expectedResult));
    }

}