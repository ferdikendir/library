package com.example.library.dto.bookUser;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookUserAddRequestModel {

    private String user_id;
    private String book_id;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
}
