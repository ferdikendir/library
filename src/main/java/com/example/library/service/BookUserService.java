package com.example.library.service;

import com.example.library.dto.bookUser.BookUserAddRequestModel;
import com.example.library.dto.bookUser.BookUserUpdateRequestModel;
import com.example.library.entity.Book;
import com.example.library.entity.BookUser;
import com.example.library.entity.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BookUserRepository;
import com.example.library.repository.UserRepository;
import com.example.library.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class BookUserService {

    private final BookUserRepository bookUserRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final HttpServletRequest request;
    private final JwtUtil jwtUtil;

    public BookUserService(
            BookUserRepository bookUserRepository,
            UserRepository userRepository,
            BookRepository bookRepository,
            HttpServletRequest request,
            JwtUtil jwtUtil) {
        this.bookUserRepository = bookUserRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.request = request;
        this.jwtUtil = jwtUtil;
    }

    public List<BookUser> getMyBook() {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        UUID userId = jwtUtil.extractSystemUserId(token);

        return bookUserRepository.findByUser_Id(userId);
    }

    public List<BookUser> findAll() {
        return bookUserRepository.findAll();
    }

    public BookUser insert(BookUserAddRequestModel bookUserAddRequestModel) {

        User user = userRepository.findById(UUID.fromString(bookUserAddRequestModel.getUser_id())).get();

        Book book = bookRepository.findById(UUID.fromString(bookUserAddRequestModel.getBook_id())).get();

        BookUser bookUser = new BookUser();

        bookUser.setUser(user);
        bookUser.setBook(book);
        bookUser.setBorrowedDate(LocalDate.now());
        bookUser.setDueDate(LocalDate.parse(bookUserAddRequestModel.getDueDate().toString()));
        bookUser.setReturned(false);

        return bookUserRepository.save(bookUser);

    }

    public BookUser update(BookUserUpdateRequestModel bookUserUpdateRequestModel) {
        User user = userRepository.findById(bookUserUpdateRequestModel.getUser_id()).get();

        Book book = bookRepository.findById(bookUserUpdateRequestModel.getBook_id()).get();

        BookUser bookUser = new BookUser();
        bookUser.setUser(user);
        bookUser.setBook(book);
        bookUser.setBorrowedDate(bookUserUpdateRequestModel.getBorrowedDate());
        bookUser.setDueDate(bookUserUpdateRequestModel.getDueDate());

        return bookUserRepository.save(bookUser);
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

        if (bookUser.getDueDate().isBefore(LocalDate.now())) {
            return true;
        }

        return false;

    }

    public BookUser markAsReturned(UUID id) {

        BookUser bookUser = bookUserRepository.getById(id);

        bookUser.setDueDate(LocalDate.now());
        bookUser.setReturned(true);
        return bookUserRepository.save(bookUser);
    }
}
