package com.artemyakkonen.client.rabbbitmq;


import com.artemyakkonen.client.dto.RabbitMessageDTO;
import com.artemyakkonen.client.service.IdentifierService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.*;

@Component
    public class MessageProducer {

        private final RabbitTemplate rabbitTemplate;
        private final IdentifierService identifierService;

        @Autowired
        public MessageProducer(RabbitTemplate rabbitTemplate, IdentifierService identifierService) {
            this.rabbitTemplate = rabbitTemplate;
            this.identifierService = identifierService;
        }

        public void sendMessage(String message) {
            RabbitMessageDTO rabbitMessageDTO = RabbitMessageDTO.builder()
                    .uuid(identifierService.getServiceId())
                    .timestamp(LocalDateTime.now(Clock.system(ZoneId.of("Europe/Moscow"))))
                    .body(message)
                    .build();

            rabbitTemplate.convertAndSend("myExchange", "routingKey", rabbitMessageDTO);
            System.out.println("Sent: " + rabbitMessageDTO.getBody());
        }
    }

