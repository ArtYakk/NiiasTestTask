package com.artemyakkonen.server.service;

import com.artemyakkonen.server.dto.MessageRequest;
import com.artemyakkonen.server.dto.MessageResponse;
import com.artemyakkonen.server.entity.Message;
import com.artemyakkonen.server.entity.User;
import com.artemyakkonen.server.mapper.MessageMapper;
import com.artemyakkonen.server.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    MessageRepository messageRepository;
    UserService userService;

    @Autowired
    public MessageService(MessageRepository messageRepository, UserService userService) {
        this.messageRepository  = messageRepository;
        this.userService = userService;
    }

    public List<MessageResponse> getMessagesByUserId(Long userId) {
        return messageRepository.findByUser_Id(userId).stream().map(MessageMapper::toResponse).collect(Collectors.toList());
    }

    public MessageResponse getMessageById(Long userId) {
        return MessageMapper.toResponse(messageRepository.findById(userId).orElse(null));
    }

    @Transactional
    public void deleteMessageById(Long messageId) {
        messageRepository.deleteById(messageId);
    }

    @Transactional
   public MessageResponse saveMessage(Long userId, MessageRequest messageRequest) {
        User user = userService.getUserById(userId);
        if(user == null) {
            return null;
        }
        Message message = MessageMapper.fromRequest(messageRequest);
        user.addMessage(message);
        return MessageMapper.toResponse(messageRepository.save(message));
   }



}
