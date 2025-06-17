package com.artemyakkonen.spring.boot.ttmicroservice2.mapper;

import com.artemyakkonen.spring.boot.ttmicroservice2.dto.UserRequest;
import com.artemyakkonen.spring.boot.ttmicroservice2.dto.UserResponse;
import com.artemyakkonen.spring.boot.ttmicroservice2.entity.User;
import com.artemyakkonen.spring.boot.ttmicroservice2.entity.Activity;
import com.artemyakkonen.spring.boot.ttmicroservice2.entity.Message;

import java.util.ArrayList;

public class UserMapper {

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