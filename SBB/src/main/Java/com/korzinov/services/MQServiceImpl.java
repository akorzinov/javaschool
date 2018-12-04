package com.korzinov.services;

import com.korzinov.models.RouteModel;
import com.korzinov.models.TrainInfoModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MQServiceImpl implements MQService {

    static final Logger logger = LogManager.getLogger(MQServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(RowEditEvent event, List<RouteModel> listSchedules) {
        try {
            RouteModel rm = (RouteModel)event.getObject();
            TrainInfoModel message = new TrainInfoModel(rm);
            if (listSchedules.isEmpty()) {
                logger.info("List schedule is empty, nothing to send");
                return;
            }
            message.setScheduleId(listSchedules.get(0).getScheduleId());
            message.setStationDep(listSchedules.get(0).getStationName());
            message.setStationDest(listSchedules.get(listSchedules.size()-1).getStationName());
            message.setStatus("Late");
            rabbitTemplate.convertAndSend(message);
            logger.info(message + "has been sent");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendCanceled(RouteModel route, List<RouteModel> listSchedules) {
        try {
            TrainInfoModel message = new TrainInfoModel(route);
            if (listSchedules.isEmpty()) {
                logger.info("List schedule is empty, nothing to send");
                return;
            }
            message.setScheduleId(listSchedules.get(0).getScheduleId());
            message.setStationDep(listSchedules.get(0).getStationName());
            message.setStationDest(listSchedules.get(listSchedules.size()-1).getStationName());
            message.setStatus("Canceled");
            rabbitTemplate.convertAndSend(message);
            logger.info(message + "has been sent");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RabbitTemplate getRabbitTemplate() {
        return rabbitTemplate;
    }

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
