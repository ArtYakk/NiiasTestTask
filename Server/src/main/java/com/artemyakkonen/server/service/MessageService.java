package com.artemyakkonen.server.service;

import com.artemyakkonen.server.dto.MessageRequest;
import com.artemyakkonen.server.dto.MessageResponse;
import com.artemyakkonen.server.entity.Message;
import com.artemyakkonen.server.entity.User;
import com.artemyakkonen.server.mapper.MessageMapper;
import com.artemyakkonen.server.repository.MessageRepository;
import com.artemyakkonen.server.util.AnsiColors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;
    private final MessageMapper messageMapper;

    @Autowired
    public MessageService(MessageRepository messageRepository, UserService userService, MessageMapper messageMapper) {
        this.messageRepository  = messageRepository;
        this.userService = userService;
        this.messageMapper = messageMapper;
    }

    public List<MessageResponse> getMessagesByUserId(Long userId) {
        List<Message> messageList = messageRepository.findByUser_Id(userId);
        for(Message message : messageList){
            log.info(AnsiColors.blackOnBlue(message.getId() + " " + message.getUser().getId()));
        }

        return  messageMapper.toResponseList(messageList);
    }

    public MessageResponse getMessageById(Long userId) {
        return messageMapper.toResponse(messageRepository.findById(userId).orElse(null));
    }

    @Transactional
    public void deleteMessageById(Long messageId) {
        messageRepository.deleteById(messageId);
    }

    @Transactional
   public MessageResponse saveMessage(Long userId, MessageRequest messageRequest) {
        Optional<User> userOptional = userService.getUserById(userId);
        if(userOptional.isEmpty()) {
            return null;
        }
        User user = userOptional.get();
        Message message = messageMapper.fromRequest(messageRequest);
        user.addMessage(message);
        return messageMapper.toResponse(messageRepository.save(message));
   }



}
