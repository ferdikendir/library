package com.example.library.service;

import com.example.library.dto.book.BookListResponseModel;
import com.example.library.dto.book.BookRequestModel;
import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BookUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookUserService bookUserService;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, BookUserService bookUserService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookUserService = bookUserService;
    }

    public List<BookListResponseModel> findAll() {

        List<Book> books = bookRepository.findAll();

        List<BookListResponseModel> bookListResponseModels = new ArrayList<>();

        for (Book book : books) {
            BookListResponseModel bookListResponseModel = new BookListResponseModel();

            bookListResponseModel.setId(book.getId());
            bookListResponseModel.setIsbn(book.getIsbn());
            bookListResponseModel.setTitle(book.getTitle());
            bookListResponseModel.setEdition(book.getEdition());
            bookListResponseModel.setYear(book.getYear());
            bookListResponseModel.setAuthor_id(book.getAuthor().getId());
            bookListResponseModel.setAuthor(book.getAuthor());

            bookListResponseModel.setAvailable(bookUserService.checkBorrowBook(book.getId()));

            bookListResponseModels.add(bookListResponseModel);
        }

        return bookListResponseModels;
    }

    public Book insert(BookRequestModel bookRequestModel) {

        if(bookRepository.findByIsbn(bookRequestModel.getIsbn()).isPresent()) {
            throw new RuntimeException("Isbn already exists");
        }

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
