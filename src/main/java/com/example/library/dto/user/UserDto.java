package com.example.library.dto.user;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {

    private UUID id;
    private String username;
    private String name;
    private String surname;
    private String role;

}
