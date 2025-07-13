package com.artemyakkonen.client.rabbbitmq;

import com.artemyakkonen.client.service.IdentifierService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
@EnableScheduling
public class ScheduledMessageProducer {

    private final RabbitTemplate rabbitTemplate;
    private final IdentifierService identifierService;

    @Autowired
    public ScheduledMessageProducer(RabbitTemplate rabbitTemplate, IdentifierService identifierService) {
        this.rabbitTemplate = rabbitTemplate;
        this.identifierService = identifierService;
    }

    @Scheduled(fixedRate = 10000)
    public void sendScheduledMessage() {
        RabbitMessage rabbitMessage = RabbitMessage.builder()
                .uuid(identifierService.getServiceId())
                .timestamp(LocalDateTime.now(ZoneId.of("Europe/Moscow")))
                .body("Activity")
                .build();
        rabbitTemplate.convertAndSend("myExchange", "routingKey", rabbitMessage);
        System.out.println("Sent: " + rabbitMessage.getBody());
    }
}

