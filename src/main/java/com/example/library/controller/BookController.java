package com.example.library.controller;

import com.example.library.dto.book.BookDto;
import com.example.library.dto.book.BookRequestModel;
import com.example.library.service.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
