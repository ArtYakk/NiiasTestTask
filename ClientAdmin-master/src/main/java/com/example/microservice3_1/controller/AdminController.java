package com.example.microservice3_1.controller;

import com.example.microservice3_1.dto.MessageResponse;
import com.example.microservice3_1.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "User API", description = "Управление пользователями и сообщениями")
public class AdminController {
private static final URI BASE_URI = URI.create("http://localhost:8081/");

    RestTemplate restTemplate;

    @Autowired
    public AdminController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/users/all")
    @Operation(
            summary = "Получить список зарегистрированных пользователей",
            description = "Возвращает пользователей которые есть в БД"
    )
    @ApiResponse(responseCode = "200", description = "Пользователи найдены")
    public ResponseEntity<List<UserResponse>> getAllRegisteredUsers(){
        return restTemplate.exchange(
                BASE_URI.resolve("/users"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserResponse>>() {}
        );
    }

    @GetMapping("/users/active")
    @Operation(
            summary = "Получить список активных пользователей",
            description = "Возвращает пользователей у которых в таблице активностей есть хоть одна запись, " +
                    "если удалить пользователя, все его сообщения и записи об активности каскадно удалятся " +
                    "а значит он станет неактивным"
    )
    @ApiResponse(responseCode = "200", description = "Пользователи найдены")
    public ResponseEntity<List<UserResponse>> getActiveUsers(){
        return restTemplate.exchange(
                BASE_URI.resolve("/users/active"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserResponse>>() {}
        );
    }

    @GetMapping("/messages/{requestedId}")
    @Operation(
            summary = "Получить сообщения пользователя по его ID",
            description = "Возвращает все сообщения указанного пользователя"
    )
    @ApiResponse(responseCode = "200", description = "Если пользователь найден")
    @ApiResponse(responseCode = "404", description = "Если пользователь не найден")
    public ResponseEntity<List<MessageResponse>> getUsersMessages(@PathVariable Long requestedId){
        return restTemplate.exchange(
                UriComponentsBuilder.fromUri(BASE_URI)
                        .path("/messages/")
                        .path(Long.toString(requestedId))
                        .build()
                        .toUri(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MessageResponse>>() {}
        );
    }

    @DeleteMapping("/users/{requestedId}")
    @Operation(
            summary = "Удалить пользователя",
            description = "Удаляет пользователя с указанным идентификатором"
    )
    @ApiResponse(responseCode = "204", description = "Пользователь удален")
    @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    public ResponseEntity<Void> deleteUser(@PathVariable Long requestedId){
        return restTemplate.exchange(
                UriComponentsBuilder.fromUri(BASE_URI)
                        .path("/users/")
                        .path(Long.toString(requestedId))
                        .build()
                        .toUri(),
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }

    @DeleteMapping("/messages/{requestedId}")
    @Operation(
            summary = "Удалить сообщение",
            description = "Удлаляет сообщение с указанным идентификатором"
    )
    @ApiResponse(responseCode = "204", description = "Сообщение удалено")
    @ApiResponse(responseCode = "404", description = "Сообщение не найдено")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long requestedId){
        return restTemplate.exchange(
                UriComponentsBuilder.fromUri(BASE_URI)
                        .path("/messages/")
                        .path(Long.toString(requestedId))
                        .build()
                        .toUri(),
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }

}
