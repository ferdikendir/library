package com.example.library.controller;

import com.example.library.dto.bookUser.BookUserAddRequestModel;
import com.example.library.dto.bookUser.BookUserDto;
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
@RequestMapping("/api/BookUser")
public class BookUserController {

    private final BookUserService bookUserService;

    public BookUserController(BookUserService bookUserService) {
        this.bookUserService = bookUserService;
    }

    @PostMapping("/MyBookList")
    public List<BookUserDto> getMyBoookList() {
        return  bookUserService.findAll();
    }

    @PostMapping("/List")
    public List<BookUserDto> list() {
        return  bookUserService.findAll();
    }

    @PostMapping("/Insert")
    public BookUserDto insertBookUser(@RequestBody BookUserAddRequestModel bookUserAddRequestModel) {
        return  bookUserService.insert(bookUserAddRequestModel);
    }

    @PostMapping("/Update")
    public BookUserDto updateBookUser(@RequestBody BookUserUpdateRequestModel bookUserUpdateRequestModel) {
        return  bookUserService.update(bookUserUpdateRequestModel);
    }

    @PostMapping("/CheckBook")
    public boolean checkBookControl(@RequestBody BookUser bookUser) {
        return bookUserService.checkBorrowBook(bookUser.getId());
    }

    @PostMapping("/MarkAsReturned")
    public BookUserDto markBookAsReturned(@RequestBody BookUserUpdateRequestModel bookUserUpdateRequestModel) {
        return bookUserService.markAsReturned(bookUserUpdateRequestModel.getId());
    }

}
