package com.example.library.controller;

import com.example.library.dto.user.UserDto;
import com.example.library.dto.user.UserUpdateRequest;
import com.example.library.entity.User;
import com.example.library.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/User")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/List")
    public List<UserDto> list() {
        return userService.list();
    }

    @PostMapping("/Update")
    public UserDto update(@RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.update(userUpdateRequest);
    }
}
