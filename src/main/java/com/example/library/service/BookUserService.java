package com.example.library.service;

import com.example.library.dto.bookUser.BookUserAddRequestModel;
import com.example.library.dto.bookUser.BookUserUpdateRequestModel;
import com.example.library.entity.Book;
import com.example.library.entity.BookUser;
import com.example.library.entity.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BookUserRepository;
import com.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookUserService {

    private final BookUserRepository bookUserRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public BookUserService(BookUserRepository bookUserRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.bookUserRepository = bookUserRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<BookUser> findAll() {
        return bookUserRepository.findAll();
    }

    public BookUser insert(BookUserAddRequestModel bookUserAddRequestModel) {

        User user = userRepository.findById(bookUserAddRequestModel.getUser_id()).get();

        Book book = bookRepository.findById(bookUserAddRequestModel.getBook_id()).get();

        BookUser bookUser = new BookUser();

        bookUser.setUser(user);
        bookUser.setBook(book);
        bookUser.setBorrowedDate(LocalDate.now());
        bookUser.setDueDate(bookUserAddRequestModel.getDueDate());

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
}
