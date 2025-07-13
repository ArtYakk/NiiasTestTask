package com.artemyakkonen.server.rabbitmq;

import com.artemyakkonen.server.dto.ActivityRequest;
import com.artemyakkonen.server.dto.MessageRequest;
import com.artemyakkonen.server.dto.UserRequest;
import com.artemyakkonen.server.dto.UserResponse;

import com.artemyakkonen.server.entity.ActivityType;
import com.artemyakkonen.server.service.ActivityService;
import com.artemyakkonen.server.service.MessageService;
import com.artemyakkonen.server.service.UserService;
import com.artemyakkonen.server.util.AnsiColors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

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
    public void receiveMessage(RabbitMessage rabbitMessage) {
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
