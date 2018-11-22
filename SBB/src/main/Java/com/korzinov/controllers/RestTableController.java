package com.korzinov.controllers;

import com.korzinov.models.FindTrain;
import com.korzinov.services.BoardStationService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;
import javax.ws.rs.core.MediaType;

@Path("/trains")
public class RestTableController {

    @Autowired
    private BoardStationService boardStationService;

    @GET
//    @Produces(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public List<FindTrain> getListTrains() {
        return boardStationService.listTrainsToBoard();
    }

    public BoardStationService getBoardStationService() {
        return boardStationService;
    }

    public void setBoardStationService(BoardStationService boardStationService) {
        this.boardStationService = boardStationService;
    }
}
