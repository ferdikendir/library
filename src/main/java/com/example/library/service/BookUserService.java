package com.example.library.service;

import com.example.library.dto.bookUser.*;
import com.example.library.entity.*;
import com.example.library.mapper.BookUserMapper;
import com.example.library.repository.*;
import com.example.library.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class BookUserService {

    private final BookUserRepository bookUserRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final HttpServletRequest request;
    private final JwtUtil jwtUtil;
    private final BookUserMapper bookUserMapper;

    public BookUserService(
            BookUserRepository bookUserRepository,
            UserRepository userRepository,
            BookRepository bookRepository,
            HttpServletRequest request,
            JwtUtil jwtUtil,
            BookUserMapper bookUserMapper) {
        this.bookUserRepository = bookUserRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.request = request;
        this.jwtUtil = jwtUtil;
        this.bookUserMapper = bookUserMapper;
    }

    public List<BookUserDto> getMyBook() {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        UUID userId = jwtUtil.extractSystemUserId(token);

        List<BookUser> bookUsers = bookUserRepository.findByUser_Id(userId);

        List<BookUserDto> bookUserDtos = new ArrayList<>();
        for (BookUser bookUser : bookUsers) {
            bookUserDtos.add(bookUserMapper.toBookUser(bookUser));
        }

        return bookUserDtos;
    }

    public List<BookUserDto> findAll() {
        List<BookUser> bookUsers =bookUserRepository.findAll();

        List<BookUserDto> bookUserDtos = new ArrayList<>();

        for (BookUser bookUser : bookUsers) {
            bookUserDtos.add(bookUserMapper.toBookUser(bookUser));
        }

        return bookUserDtos;
    }

    public BookUserDto insert(BookUserAddRequestModel bookUserAddRequestModel) {

        User user = findUser(UUID.fromString(bookUserAddRequestModel.getUser_id()));

        Book book = findBook(UUID.fromString(bookUserAddRequestModel.getBook_id()));

        BookUser bookUser = new BookUser();

        bookUser.setUser(user);
        bookUser.setBook(book);
        bookUser.setBorrowedDate(LocalDate.now());
        bookUser.setDueDate(LocalDate.parse(bookUserAddRequestModel.getDueDate().toString()));
        bookUser.setReturned(false);

        return bookUserMapper.toBookUser(bookUserRepository.save(bookUser));

    }

    public BookUserDto update(BookUserUpdateRequestModel bookUserUpdateRequestModel) {
        User user = findUser(UUID.fromString(bookUserUpdateRequestModel.getUser_id()));

        Book book = findBook(UUID.fromString(bookUserUpdateRequestModel.getBook_id()));

        BookUser bookUser = new BookUser();

        bookUser.setUser(user);
        bookUser.setBook(book);
        bookUser.setBorrowedDate(bookUserUpdateRequestModel.getBorrowedDate());
        bookUser.setDueDate(bookUserUpdateRequestModel.getDueDate());

        return bookUserMapper.toBookUser(bookUserRepository.save(bookUser));
    }

    public boolean checkBorrowBook(UUID bookId) {
        Book book = bookRepository.findById(bookId).get();

        BookUser bookUser = bookUserRepository.borrowBookNow(book);

        if (bookUser == null) {
            return true;
        }

        if (bookUser.isReturned()) {
            return true;
        }

        return bookUser.getDueDate().isBefore(LocalDate.now());

    }

    public BookUserDto markAsReturned(UUID id) {

        BookUser bookUser = bookUserRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Book does not exist")
        );

        bookUser.setDueDate(LocalDate.now());
        bookUser.setReturned(true);

        return bookUserMapper.toBookUser(bookUserRepository.save(bookUser));
    }

    private User findUser(UUID id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
    }

    private Book findBook(UUID id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Book not found")
        );
    }
}
