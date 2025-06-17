package com.artemyakkonen.spring.boot.ttmicroservice2.rabbitmq;

import com.artemyakkonen.spring.boot.ttmicroservice2.dto.ActivityRequest;
import com.artemyakkonen.spring.boot.ttmicroservice2.dto.MessageRequest;
import com.artemyakkonen.spring.boot.ttmicroservice2.dto.UserRequest;
import com.artemyakkonen.spring.boot.ttmicroservice2.dto.UserResponse;
import com.artemyakkonen.spring.boot.ttmicroservice2.dto.rabbit.RabbitMessageDTO;

import com.artemyakkonen.spring.boot.ttmicroservice2.entity.ActivityType;
import com.artemyakkonen.spring.boot.ttmicroservice2.service.ActivityService;
import com.artemyakkonen.spring.boot.ttmicroservice2.service.MessageService;
import com.artemyakkonen.spring.boot.ttmicroservice2.service.UserService;
import com.artemyakkonen.spring.boot.ttmicroservice2.util.AnsiColors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class MessageConsumerService {

    private final UserService userService; // Сервис для работы с пользователями
    private final MessageService messageService;
    private final ActivityService activityService;

    @Autowired
    public MessageConsumerService(UserService userService, MessageService messageService, ActivityService activityService) {
        this.userService = userService;
        this.messageService = messageService;
        this.activityService = activityService;
    }

    @RabbitListener(queues = "myQueue")
    public void receiveMessage(RabbitMessageDTO rabbitMessage) {
        log.info(AnsiColors.blackOnBlue("Received: " + rabbitMessage.getBody()));
        UserResponse userResponse = userService.getUserByUuid(rabbitMessage.getUuid());

        if (userResponse != null) {
            MessageRequest messageRequest = MessageRequest.builder()
                    .user_id(userResponse.getId())
                    .body(rabbitMessage.getBody())
                    .time(rabbitMessage.getTimestamp())
                    .build();
            messageService.saveMessage(userResponse.getId(), messageRequest);
            ActivityRequest activityRequest = ActivityRequest.builder()
                    .user_id(userResponse.getId())
                    .time(rabbitMessage.getTimestamp())
                    .type(rabbitMessage.getBody().equals("Activity") ? ActivityType.ACTIVITY_STATUS : ActivityType.MESSAGE)
                    .build();
            activityService.saveActivity(userResponse.getId(), activityRequest);
        }else {
            UserRequest userRequest = UserRequest.builder()
                    .role("USER")
                    .uuid(rabbitMessage.getUuid())
                    .build();
            userService.addUser(userRequest);
        }

    }

}
