package com.example.library.dto.user;

import lombok.Data;

import java.util.UUID;

@Data
public class UserUpdateRequest {

    private UUID id;
    private String name;
    private String surname;
}
