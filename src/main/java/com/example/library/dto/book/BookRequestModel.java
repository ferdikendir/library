package com.example.library.dto.book;

import lombok.Data;

@Data
public class BookRequestModel {
    private String isbn;
    private String title;
    private String edition;
    private String year;
    private String author_id;

}
