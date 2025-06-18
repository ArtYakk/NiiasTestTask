package com.artemyakkonen.server.controller;

import com.artemyakkonen.server.dto.MessageResponse;
import com.artemyakkonen.server.dto.UserResponse;
import com.artemyakkonen.server.entity.ActivityType;
import com.artemyakkonen.server.entity.User;
import com.artemyakkonen.server.service.ActivityService;
import com.artemyakkonen.server.service.MessageService;
import com.artemyakkonen.server.service.UserService;
import com.artemyakkonen.server.util.AnsiColors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/server_api")
public class Controller {
    UserService userService;
    MessageService messageService;
    ActivityService activityService;

    @Autowired
    public Controller(UserService userService, MessageService messageService, ActivityService activityService) {
        this.userService = userService;
        this.messageService = messageService;
        this.activityService = activityService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        log.info(AnsiColors.blackOnBlue("Get all users"));

        activityService.saveAdminsActivity(ActivityType.GET_ALL_USERS);

       return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/active")
    public ResponseEntity<List<UserResponse>> getActiveUsers() {
        log.info(AnsiColors.blackOnBlue("Get active users"));

        activityService.saveAdminsActivity(ActivityType.GET_ACTIVE_USERS);

        return ResponseEntity.ok(userService.getActiveUsers());
    }

    @GetMapping("/messages/{requestedId}")
    public ResponseEntity<List<MessageResponse>> getMessagesByUserId(@PathVariable Long requestedId) {
        log.info(AnsiColors.blackOnBlue("Get messages of user with requested id " + requestedId));

        activityService.saveAdminsActivity(ActivityType.GET_MESSAGES_BY_USER_ID);

        User user = userService.getUserById(requestedId);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(messageService.getMessagesByUserId(requestedId));
    }

    @DeleteMapping("/users/{requestedId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long requestedId) {
        log.info(AnsiColors.blackOnBlue("Attempt to delete user with requested id " + requestedId));

        activityService.saveAdminsActivity(ActivityType.DELETE_USER_BY_ID);

        User user = userService.getUserById(requestedId);
        if(user != null) {
            userService.deleteUserById(requestedId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/messages/{requestedId}")
    public ResponseEntity<Void> deleteMessageById(@PathVariable Long requestedId) {
        log.info(AnsiColors.blackOnBlue("Attempt to delete message with requested id " + requestedId));

        activityService.saveAdminsActivity(ActivityType.DELETE_MESSAGE_BY_ID);

        MessageResponse messageResponse = messageService.getMessageById(requestedId);
        if(messageResponse != null) {
            messageService.deleteMessageById(requestedId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }



}
