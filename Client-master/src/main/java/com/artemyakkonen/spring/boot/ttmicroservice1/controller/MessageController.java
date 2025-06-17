package com.artemyakkonen.spring.boot.ttmicroservice1.controller;

import com.artemyakkonen.spring.boot.ttmicroservice1.rabbbitmq.MessageProducer;
import com.artemyakkonen.spring.boot.ttmicroservice1.service.IdentifierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MessageController {

    MessageProducer messageProducer;

    @Autowired
    public MessageController(MessageProducer messageProducer, IdentifierService identifierService){
        this.messageProducer = messageProducer;
    }

    @PostMapping("/message")
    private ResponseEntity<String> sendMessage(@RequestBody(required = false) String message){
        if(message == null || message.equals("")){
            ResponseEntity.badRequest();
        }
        messageProducer.sendMessage("Message: " + message);
        return ResponseEntity.ok("Message sent");
    }
}
