package com.artemyakkonen.client.rabbbitmq;


import com.artemyakkonen.client.service.IdentifierService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.*;

@Component
    public class MessageProducer {

        private final RabbitTemplate rabbitTemplate;
        private final IdentifierService identifierService;

        public MessageProducer(RabbitTemplate rabbitTemplate, IdentifierService identifierService) {
            this.rabbitTemplate = rabbitTemplate;
            this.identifierService = identifierService;
        }

        public void sendMessage(String message) {
            RabbitMessage rabbitMessage = RabbitMessage.builder()
                    .uuid(identifierService.getServiceId())
                    .timestamp(LocalDateTime.now(Clock.system(ZoneId.of("Europe/Moscow"))))
                    .body(message)
                    .build();

            rabbitTemplate.convertAndSend("myExchange", "routingKey", rabbitMessage);
            System.out.println("Sent: " + rabbitMessage.getBody());
        }
    }

