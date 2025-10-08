package com.example.library.dto.author;

import lombok.Data;

import java.util.UUID;

@Data
public class AuthorUpdateRequest {
    private UUID id;
    private String name;
    private String surname;
}
