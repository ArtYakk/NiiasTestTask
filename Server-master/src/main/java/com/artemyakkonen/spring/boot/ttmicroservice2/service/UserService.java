package com.artemyakkonen.spring.boot.ttmicroservice2.service;

import com.artemyakkonen.spring.boot.ttmicroservice2.dto.UserRequest;
import com.artemyakkonen.spring.boot.ttmicroservice2.dto.UserResponse;
import com.artemyakkonen.spring.boot.ttmicroservice2.entity.User;
import com.artemyakkonen.spring.boot.ttmicroservice2.mapper.UserMapper;
import com.artemyakkonen.spring.boot.ttmicroservice2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream().map(UserMapper::toResponse).collect(Collectors.toList());
    }

    public List<UserResponse> getActiveUsers(){
        return userRepository.findByActivitiesIsNotEmpty().stream().map(UserMapper::toResponse).collect(Collectors.toList());
    }

    public void deleteUserById(Long id){
            userRepository.deleteById(id);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public UserResponse getUserByUuid(String uuid){
      return UserMapper.toResponse(userRepository.findByUuid(uuid).orElse(null));
    }

    @Transactional
    public User getUserByUuidNoDto(String uuid){
        return userRepository.findByUuid(uuid).orElse(null);
    }

    @Transactional
    public UserResponse addUser(UserRequest userRequest){
       User savedUser = userRepository.save(UserMapper.fromRequest(userRequest));
        return UserMapper.toResponse(savedUser);
    }


}
