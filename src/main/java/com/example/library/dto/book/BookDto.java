package com.example.library.dto.book;

import com.example.library.dto.author.AuthorDto;
import lombok.Data;

import java.util.UUID;

@Data
public class BookDto {

    private UUID id;
    private String isbn;
    private String title;
    private String edition;
    private String year;
    private UUID author_id;
    private AuthorDto author;
    private boolean available;
}
