package com.example.library.controller;

import com.example.library.dto.book.*;
import com.example.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/List")
    public List<BookDto> getBooks() {
        return bookService.findAll();
    }

    @PostMapping("/Insert")
    public BookDto insert(@RequestBody BookRequestModel bookRequestModel) {
        return  bookService.insert(bookRequestModel);
    }

    @PostMapping("/Update")
    public BookDto update(@RequestBody BookRequestModel bookRequestModel) {
        return  bookService.update(bookRequestModel);
    }


}
