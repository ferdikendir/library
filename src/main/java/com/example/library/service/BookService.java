package com.example.library.service;

import com.example.library.dto.book.BookRequestModel;
import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> findAll() {

        List<Book> books = bookRepository.findAll();

        return books;
    }

    public Book insert(BookRequestModel bookRequestModel) {
        Book book = new Book();
        book.setTitle(bookRequestModel.getTitle());
        book.setYear(bookRequestModel.getYear());
        book.setIsbn(bookRequestModel.getIsbn());
        book.setEdition(bookRequestModel.getEdition());

        Author author = authorRepository.findById( UUID.fromString(bookRequestModel.getAuthor_id())).get();

        book.setAuthor(author);

        return bookRepository.save(book);

    }

    public Book update(BookRequestModel bookRequestModel) {

        Book updatedBook = bookRepository.findByIsbn(bookRequestModel.getIsbn()).get();

        updatedBook.setTitle(bookRequestModel.getTitle());
        updatedBook.setYear(bookRequestModel.getYear());
        updatedBook.setIsbn(bookRequestModel.getIsbn());
        updatedBook.setEdition(bookRequestModel.getEdition());

        Author author = authorRepository.findById( UUID.fromString(bookRequestModel.getAuthor_id())).get();

        updatedBook.setAuthor(author);

        return bookRepository.save(updatedBook);

    }

}
