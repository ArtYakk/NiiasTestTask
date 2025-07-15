package com.artemyakkonen.server.controller;

import com.artemyakkonen.server.dto.UserResponse;
import com.artemyakkonen.server.service.ActivityService;
import com.artemyakkonen.server.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ServerControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private ActivityService activityService;

    @InjectMocks
    private ServerController serverController;


    @Test
    void getAllUsers() {
        UserResponse user1 = UserResponse.builder()
                .id(1L)
                .uuid("testUuid1")
                .role("testRole1")
                .activityIds(Arrays.asList(1L, 2L, 3L))
                .messageIds(Arrays.asList(1L, 2L, 3L))
                .build();
        UserResponse user2 = UserResponse.builder()
                .id(2L)
                .uuid("testUuid2")
                .role("testRole2")
                .activityIds(Arrays.asList(4L, 5L, 6L))
                .messageIds(Arrays.asList(4L, 5L, 6L))
                .build();
        List<UserResponse> expectedUsers = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(expectedUsers);

        List<UserResponse> actualUsers = serverController.getAllUsers().getBody();

        assertEquals(expectedUsers, actualUsers);



    }
}