package com.artemyakkonen.server.mapper;

import com.artemyakkonen.server.dto.MessageRequest;
import com.artemyakkonen.server.dto.MessageResponse;
import com.artemyakkonen.server.entity.Message;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageResponse toResponse(Message message);
    Message fromRequest(MessageRequest messageRequest);
    List<MessageResponse> toResponseList(List<Message> messageList);
}
