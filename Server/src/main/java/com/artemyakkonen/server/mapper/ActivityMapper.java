package com.artemyakkonen.server.mapper;

import com.artemyakkonen.server.dto.ActivityRequest;
import com.artemyakkonen.server.dto.ActivityResponse;
import com.artemyakkonen.server.entity.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActivityMapper {
    @Mapping(source = "user.id", target="user_id")
    ActivityResponse toResponse(Activity activity);
    Activity fromRequest(ActivityRequest activityRequest);
}
