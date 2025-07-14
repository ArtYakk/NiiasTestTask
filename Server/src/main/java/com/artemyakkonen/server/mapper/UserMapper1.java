package com.artemyakkonen.server.mapper;

import com.artemyakkonen.server.dto.UserRequest;
import com.artemyakkonen.server.dto.UserResponse;
import com.artemyakkonen.server.entity.User;
import com.artemyakkonen.server.entity.Activity;
import com.artemyakkonen.server.entity.Message;

import java.util.ArrayList;

public class UserMapper1 {

    public static UserResponse toResponse(User user) { //id, uuid, role ,activities, messages
        if(user == null) {
            return null;
        }

        return UserResponse.builder()
                .id(user.getId())
                .uuid(user.getUuid())
                .role(user.getRole())
                .activityIds(user.getActivities().stream().map(Activity::getId).toList())
                .messageIds(user.getMessages().stream().map(Message::getId).toList())
                .build();
    }

    public static User fromRequest(UserRequest userRequest) {
        if(userRequest == null) {
            return null;
        }

        return User.builder()
                .uuid(userRequest.getUuid())
                .role(userRequest.getRole())
                .messages(new ArrayList<>())
                .activities(new ArrayList<>())
                .build();
    }

}