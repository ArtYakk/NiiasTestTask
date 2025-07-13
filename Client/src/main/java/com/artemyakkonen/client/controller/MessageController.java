package com.artemyakkonen.client.controller;

import com.artemyakkonen.client.rabbbitmq.MessageProducer;
import com.artemyakkonen.client.service.IdentifierService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/client_api")
public class MessageController {

    MessageProducer messageProducer;

    public MessageController(MessageProducer messageProducer, IdentifierService identifierService){
        this.messageProducer = messageProducer;
    }

    @PostMapping("/message")
    private ResponseEntity<String> sendMessage(@RequestBody(required = false) String message){
        if(message == null || message.equals("")){
            log.warn("Пустое сообщение");
            ResponseEntity.badRequest();
        }
        String finalMessage = "Message: " + message;
        log.info("Отправка сообщения: {}", finalMessage);
        messageProducer.sendMessage(finalMessage);

        return ResponseEntity.ok("Message sent");
    }
}
