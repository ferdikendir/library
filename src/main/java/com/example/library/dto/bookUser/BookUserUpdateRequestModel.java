package com.example.library.dto.bookUser;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class BookUserUpdateRequestModel {

    private UUID id;
    private String user_id;
    private String book_id;
    private LocalDate borrowedDate;
    private LocalDate dueDate;

}
