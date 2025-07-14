package com.artemyakkonen.server.service;

import com.artemyakkonen.server.dto.UserRequest;
import com.artemyakkonen.server.dto.UserResponse;
import com.artemyakkonen.server.entity.User;
import com.artemyakkonen.server.mapper.UserMapper;
import com.artemyakkonen.server.mapper.UserMapper1;
import com.artemyakkonen.server.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserResponse> getAllUsers(){
        return userMapper.toResponseList(userRepository.findAll()); //userRepository.findAll().stream().map(UserMapper1::toResponse).collect(Collectors.toList());
    }

    public List<UserResponse> getActiveUsers(){
        return userMapper.toResponseList(userRepository.findByActivitiesIsNotEmpty()); //userRepository.findByActivitiesIsNotEmpty().stream().map(UserMapper1::toResponse).collect(Collectors.toList());
    }

    public void deleteUserById(Long id){
            userRepository.deleteById(id);
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    @Transactional
    public Optional<UserResponse> getUserByUuid(String uuid){
        Optional<User> userOptional = userRepository.findByUuid(uuid);
        if (userOptional.isEmpty()){
            return Optional.empty();
        }
        User user  = userOptional.get();
        return Optional.of(userMapper.toResponse(user));//UserMapper1.toResponse(userRepository.findByUuid(uuid).orElse(null));
    }

    @Transactional
    public User getUserByUuidNoDto(String uuid){
        return userRepository.findByUuid(uuid).orElse(null);
    }

    @Transactional
    public UserResponse addUser(UserRequest userRequest){
       User savedUser = userRepository.save(userMapper.fromRequest(userRequest)); //userRepository.save(UserMapper1.fromRequest(userRequest));
        return userMapper.toResponse(savedUser);
    }


}
