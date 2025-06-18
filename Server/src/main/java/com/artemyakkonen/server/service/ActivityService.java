package com.artemyakkonen.server.service;

import com.artemyakkonen.server.dto.ActivityRequest;
import com.artemyakkonen.server.dto.ActivityResponse;
import com.artemyakkonen.server.dto.UserRequest;
import com.artemyakkonen.server.entity.Activity;
import com.artemyakkonen.server.entity.ActivityType;
import com.artemyakkonen.server.entity.User;
import com.artemyakkonen.server.mapper.ActivityMapper;
import com.artemyakkonen.server.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class ActivityService {
    ActivityRepository activityRepository;
    UserService userService;

    @Autowired
    public ActivityService(ActivityRepository activityRepository, UserService userService) {
        this.activityRepository  = activityRepository;
        this.userService = userService;
    }

    @Transactional
    public ActivityResponse saveActivity(Long userId, ActivityRequest activityRequest) {
        User user = userService.getUserById(userId);
        if(user == null) {
            return null;
        }
        Activity activity = ActivityMapper.fromRequest(activityRequest);
        user.addActivity(activity);
        return  ActivityMapper.toResponse(activityRepository.save(activity));
    }

    @Transactional
    public ActivityResponse saveAdminsActivity(ActivityType type) {
        User user = userService.getUserByUuidNoDto("A777AA77");

        if(user == null) {
            UserRequest userRequest = UserRequest.builder()
                    .role("ADMIN")
                    .uuid("A777AA77")
                    .build();
            userService.addUser(userRequest);
            user = userService.getUserByUuidNoDto("A777AA77");
        }

        Activity activity = Activity.builder()
                .time(LocalDateTime.now(Clock.system(ZoneId.of("Europe/Moscow"))))
                .type(type)
                .build();
        user.addActivity(activity);

        return  ActivityMapper.toResponse(activityRepository.save(activity));
    }



}
