package com.example.library.controller;

import com.example.library.dto.user.*;
import com.example.library.service.UserService;
import org.springframework.web.bind.annotation.*;

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
