package com.korzinov.controllers;

import com.korzinov.models.TrainInfoModel;
import com.korzinov.services.BoardStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.client.ResponseCreator;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/trains")
@Controller
public class RestTableController {

    @Autowired
    private BoardStationService boardStationService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{station}/{date}")
    public Response getListTrains(@PathParam("station") String station, @PathParam("date") Long date) {
        List<TrainInfoModel> result = boardStationService.listTrainsToBoard(station, new Date(date));
        if (result == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("For station " + station + " not found trains on this date " + date).build();
        }
        return Response.ok(result).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/stations")
    public Response getListStations() {
        List<String> result = boardStationService.listStations();
        if (result == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Stations not found").build();
        }
        return Response.ok(result).build();
    }

    public BoardStationService getBoardStationService() {
        return boardStationService;
    }

    public void setBoardStationService(BoardStationService boardStationService) {
        this.boardStationService = boardStationService;
    }
}
