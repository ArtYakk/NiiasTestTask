package com.artemyakkonen.server.service;

import com.artemyakkonen.server.dto.ActivityRequest;
import com.artemyakkonen.server.dto.ActivityResponse;
import com.artemyakkonen.server.dto.UserRequest;
import com.artemyakkonen.server.entity.Activity;
import com.artemyakkonen.server.entity.ActivityType;
import com.artemyakkonen.server.entity.User;
import com.artemyakkonen.server.mapper.ActivityMapper;
import com.artemyakkonen.server.repository.ActivityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class ActivityService {
   private final ActivityRepository activityRepository;
   private final UserService userService;
   private final ActivityMapper activityMapper;

    public ActivityService(ActivityRepository activityRepository, UserService userService, ActivityMapper activityMapper) {
        this.activityRepository  = activityRepository;
        this.userService = userService;
        this.activityMapper = activityMapper;
    }

    @Transactional
    public ActivityResponse saveActivity(Long userId, ActivityRequest activityRequest) {
        Optional<User> userOptional = userService.getUserById(userId);
        if(userOptional.isEmpty()) {
            return null;
        }
        User user = userOptional.get();
        Activity activity =  activityMapper.fromRequest(activityRequest); // ActivityMapper1.fromRequest(activityRequest);
        user.addActivity(activity);
        return activityMapper.toResponse(activityRepository.save(activity)); // ActivityMapper1.toResponse(activityRepository.save(activity));
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

        return  activityMapper.toResponse(activityRepository.save(activity)); // ActivityMapper1.toResponse(activityRepository.save(activity));
    }



}
