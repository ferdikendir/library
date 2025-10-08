package com.example.library.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterResponse {

    private String name;

    private String surname;

    private String username;

    private String role;

}
