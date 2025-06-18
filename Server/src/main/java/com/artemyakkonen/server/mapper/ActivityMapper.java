package com.artemyakkonen.server.mapper;

import com.artemyakkonen.server.dto.ActivityRequest;
import com.artemyakkonen.server.dto.ActivityResponse;
import com.artemyakkonen.server.entity.Activity;

public class ActivityMapper {
    public static ActivityResponse toResponse(Activity activity) {
        if(activity == null) {
            return null;
        }

        return ActivityResponse.builder()
                .id(activity.getId())
                .user_id(activity.getUser() != null ? activity.getUser().getId() : null)
                .time(activity.getTime())
                .type(activity.getType())
                .build();
    }

    public static Activity fromRequest(ActivityRequest activityRequest) {
        if(activityRequest == null) {
            return null;
        }

        return Activity.builder()
                .time(activityRequest.getTime())
                .type(activityRequest.getType())
                .build();
    }
}
