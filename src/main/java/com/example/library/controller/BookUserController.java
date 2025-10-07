package com.example.library.controller;

import com.example.library.dto.bookUser.BookUserAddRequestModel;
import com.example.library.dto.bookUser.BookUserUpdateRequestModel;
import com.example.library.entity.BookUser;
import com.example.library.service.BookUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/book_user")
public class BookUserController {

    private final BookUserService bookUserService;

    public BookUserController(BookUserService bookUserService) {
        this.bookUserService = bookUserService;
    }

    @PostMapping("/list")
    public List<BookUser> list() {
        return  bookUserService.findAll();
    }

    @PostMapping("/insert")
    public BookUser insertBookUser(@RequestBody BookUserAddRequestModel bookUserAddRequestModel) {
        return  bookUserService.insert(bookUserAddRequestModel);
    }

    @PostMapping("/update")
    public BookUser updateBookUser(@RequestBody BookUserUpdateRequestModel bookUserUpdateRequestModel) {
        return  bookUserService.update(bookUserUpdateRequestModel);
    }

    @PostMapping("/check_book")
    public boolean checkBookControl(@RequestBody BookUser bookUser) {
        return bookUserService.checkBorrowBook(bookUser.getId());
    }

    @PostMapping("/mark_as_returned")
    public BookUser markBookAsReturned(@RequestBody BookUserUpdateRequestModel bookUserUpdateRequestModel) {
        return bookUserService.markAsReturned(bookUserUpdateRequestModel.getId());
    }

}
