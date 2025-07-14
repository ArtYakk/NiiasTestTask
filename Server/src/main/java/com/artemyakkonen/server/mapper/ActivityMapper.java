package com.artemyakkonen.server.mapper;

import com.artemyakkonen.server.dto.ActivityRequest;
import com.artemyakkonen.server.dto.ActivityResponse;
import com.artemyakkonen.server.entity.Activity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActivityMapper {
    ActivityResponse toResponse(Activity activity);
    Activity fromRequest(ActivityRequest activityRequest);
}
