package com.example.library.dto.bookUser;

import com.example.library.dto.book.BookDto;
import com.example.library.dto.user.UserDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class BookUserDto {
    private UUID id;
    private BookDto book;
    private UserDto user;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
    private boolean returned;
}
