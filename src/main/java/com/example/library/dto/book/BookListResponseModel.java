package com.example.library.dto.book;

import com.example.library.entity.Author;
import lombok.Data;
import java.util.UUID;

@Data
public class BookListResponseModel {

    private UUID id;
    private String isbn;
    private String title;
    private String edition;
    private String year;
    private UUID author_id;
    private Author author;
    private boolean available;

}
