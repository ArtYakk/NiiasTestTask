package com.artemyakkonen.server.mapper;

import com.artemyakkonen.server.dto.UserRequest;
import com.artemyakkonen.server.dto.UserResponse;
import com.artemyakkonen.server.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User user);
    User fromRequest(UserRequest userRequest);
    List<UserResponse> toResponseList(List<User> userList);
}
