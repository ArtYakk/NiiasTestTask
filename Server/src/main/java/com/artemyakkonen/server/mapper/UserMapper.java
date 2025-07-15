package com.artemyakkonen.server.mapper;

import com.artemyakkonen.server.dto.UserRequest;
import com.artemyakkonen.server.dto.UserResponse;
import com.artemyakkonen.server.entity.Activity;
import com.artemyakkonen.server.entity.Message;
import com.artemyakkonen.server.entity.User;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "activities", target = "activityIds", qualifiedByName = "mapActivitiesToIds")
    @Mapping(source = "messages", target = "messageIds", qualifiedByName = "mapMessagesToIds")
    UserResponse toResponse(User user);

    User fromRequest(UserRequest userRequest);
    List<UserResponse> toResponseList(List<User> userList);

    @Named("mapMessagesToIds")
    static List<Long> mapMessagesToIds(List<Message> messages){
        if(messages == null){
            return null;
        }
        return messages.stream()
                .map(Message::getId)
                .collect(Collectors.toList());
    }

    @Named("mapActivitiesToIds")
    static List<Long> mapActivitiesToIds(List<Activity> activities){
        if(activities == null){
            return null;
        }
        return activities.stream()
                .map(Activity::getId)
                .collect(Collectors.toList());
    }
}
