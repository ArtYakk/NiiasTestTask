package com.artemyakkonen.server.mapper;


import com.artemyakkonen.server.dto.MessageRequest;
import com.artemyakkonen.server.dto.MessageResponse;
import com.artemyakkonen.server.entity.Message;

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
