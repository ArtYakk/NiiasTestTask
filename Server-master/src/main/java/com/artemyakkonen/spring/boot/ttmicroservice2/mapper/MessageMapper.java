package com.artemyakkonen.spring.boot.ttmicroservice2.mapper;


import com.artemyakkonen.spring.boot.ttmicroservice2.dto.MessageRequest;
import com.artemyakkonen.spring.boot.ttmicroservice2.dto.MessageResponse;
import com.artemyakkonen.spring.boot.ttmicroservice2.entity.Message;

public class MessageMapper {

    public static MessageResponse toResponse(Message message) {
        if (message == null) {
            return null;
        }

        return MessageResponse.builder()
                .id(message.getId())
                .user_id(message.getUser() != null ? message.getUser().getId() : null)
                .body(message.getBody())
                .time(message.getTime())
                .build();
    }

    public static Message fromRequest(MessageRequest messageRequest) {
        if (messageRequest == null) {
            return null;
        }

        return Message.builder()
                .body(messageRequest.getBody())
                .time(messageRequest.getTime())
                .build();
    }
}
