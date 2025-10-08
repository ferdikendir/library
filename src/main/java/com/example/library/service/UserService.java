package com.example.library.service;

import com.example.library.dto.user.*;
import com.example.library.entity.User;
import com.example.library.mapper.UserMapper;
import com.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> list() {
        List<User> users = userRepository.list();

        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            userDtos.add(userMapper.toUserDto(user));
        }

        return userDtos;
    }

    public UserDto update(UserUpdateRequest userUpdateRequest) {

        User oldUser = userRepository.findById(userUpdateRequest.getId()).get();

        oldUser.setName(userUpdateRequest.getName());
        oldUser.setSurname(userUpdateRequest.getSurname());

        return userMapper.toUserDto(userRepository.save(oldUser));

    }
}
