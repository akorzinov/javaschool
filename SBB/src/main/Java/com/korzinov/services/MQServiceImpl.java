package com.korzinov.services;

import com.korzinov.models.MessageToMQModel;
import com.korzinov.models.RouteModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQServiceImpl implements MQService {

    static final Logger logger = LogManager.getLogger(MQServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(RowEditEvent event) {
        RouteModel rm = (RouteModel)event.getObject();
        MessageToMQModel message = new MessageToMQModel(rm);
        rabbitTemplate.convertAndSend(message);
        logger.info(message + "has been sent");
    }

    public RabbitTemplate getRabbitTemplate() {
        return rabbitTemplate;
    }

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
