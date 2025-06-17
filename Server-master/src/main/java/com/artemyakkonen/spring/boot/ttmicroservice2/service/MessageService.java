package com.artemyakkonen.spring.boot.ttmicroservice2.service;

import com.artemyakkonen.spring.boot.ttmicroservice2.dto.MessageRequest;
import com.artemyakkonen.spring.boot.ttmicroservice2.dto.MessageResponse;
import com.artemyakkonen.spring.boot.ttmicroservice2.dto.UserResponse;
import com.artemyakkonen.spring.boot.ttmicroservice2.entity.Message;
import com.artemyakkonen.spring.boot.ttmicroservice2.entity.User;
import com.artemyakkonen.spring.boot.ttmicroservice2.mapper.MessageMapper;
import com.artemyakkonen.spring.boot.ttmicroservice2.repository.MessageRepository;
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
