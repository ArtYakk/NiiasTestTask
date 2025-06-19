package com.artemyakkonen.client.controller;

import com.artemyakkonen.client.rabbbitmq.MessageProducer;
import com.artemyakkonen.client.service.IdentifierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client_api")
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
