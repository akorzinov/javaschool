package com.korzinov.controllers;

import com.korzinov.models.FindTrain;
import com.korzinov.services.BoardStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/table")
public class RestTableController {

    @Autowired
    private BoardStationService boardStationService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<FindTrain> getListTrains() {
        return boardStationService.listTrainsToBoard("Voronezh", new Date(System.currentTimeMillis()));
    }

    public BoardStationService getBoardStationService() {
        return boardStationService;
    }

    public void setBoardStationService(BoardStationService boardStationService) {
        this.boardStationService = boardStationService;
    }
}
