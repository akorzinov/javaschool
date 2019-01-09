package com.korzinov.services;

import com.korzinov.dao.StationDaoImpl;
import com.korzinov.entities.StationEntity;
import com.korzinov.models.StationModel;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;

public class StationServiceImplTest {

    @Test
    public void testFindByNameStation1() {
        List<StationEntity> stations = new ArrayList<>();
        StationEntity st = new StationEntity();
        st.setStationId(1);
        st.setStationName("Voronezh");
        stations.add(st);

        StationDaoImpl mockito = mock(StationDaoImpl.class);
        when(mockito.findByNameStation("Voronezh")).thenReturn(stations);

        StationServiceImpl stationService = new StationServiceImpl();
        stationService.setStationDao(mockito);
        List<StationModel> actualResult = stationService.findByNameStation("Voronezh");
        List<StationModel> expectedResult = new ArrayList<>();
        expectedResult.add(new StationModel(1,"Voronezh"));
        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void testFindByNameStation2() {
        List<StationEntity> stations = new ArrayList<>();
        StationEntity st = new StationEntity();
        st.setStationId(2);
        st.setStationName("Moscow");
        stations.add(st);

        StationDaoImpl mockito = mock(StationDaoImpl.class);
        when(mockito.findByNameStation("Voronezh")).thenReturn(stations);

        StationServiceImpl stationService = new StationServiceImpl();
        stationService.setStationDao(mockito);
        List<StationModel> actualResult = stationService.findByNameStation("Voronezh");
        List<StationModel> expectedResult = new ArrayList<>();
        expectedResult.add(new StationModel(1,"Voronezh"));
        assertThat(actualResult, is(not(expectedResult)));
    }
}