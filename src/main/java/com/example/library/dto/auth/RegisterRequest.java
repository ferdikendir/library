package com.example.library.dto.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String surname;
    private String username;
    private String password;
}
