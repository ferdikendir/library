package com.example.library.mapper;

import com.example.library.dto.user.UserDto;
import com.example.library.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole());

        return userDto;
    }

}
