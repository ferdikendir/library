package com.example.library.service;

import com.example.library.dto.book.*;
import com.example.library.entity.*;
import com.example.library.mapper.*;
import com.example.library.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookUserService bookUserService;
    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, BookUserService bookUserService, BookMapper bookMapper, AuthorMapper authorMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookUserService = bookUserService;
        this.bookMapper = bookMapper;
        this.authorMapper = authorMapper;
    }

    public List<BookDto> findAll() {

        List<Book> books = bookRepository.findAll();

        List<BookDto> bookDtos = new ArrayList<>();

        for (Book book : books) {
            BookDto bookDto = new BookDto();

            bookDto.setId(book.getId());
            bookDto.setIsbn(book.getIsbn());
            bookDto.setTitle(book.getTitle());
            bookDto.setEdition(book.getEdition());
            bookDto.setYear(book.getYear());
            bookDto.setAuthor_id(book.getAuthor().getId());
            bookDto.setAuthor(authorMapper.toAuthorDto(book.getAuthor()));

            bookDto.setAvailable(bookUserService.checkBorrowBook(book.getId()));

            bookDtos.add(bookDto);
        }

        return bookDtos;
    }

    public BookDto insert(BookRequestModel bookRequestModel) {

        if(bookRepository.findByIsbn(bookRequestModel.getIsbn()).isPresent()) {
            throw new RuntimeException("Isbn already exists");
        }

        Book book = new Book();
        setBookFields(book, bookRequestModel);

        return bookMapper.toBookDto(bookRepository.save(book));

    }

    public BookDto update(BookRequestModel bookRequestModel) {

        Book updatedBook = bookRepository.findByIsbn(bookRequestModel.getIsbn())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        setBookFields(updatedBook, bookRequestModel);

        return bookMapper.toBookDto(bookRepository.save(updatedBook));

    }

    private void setBookFields(Book book, BookRequestModel model) {
        book.setTitle(model.getTitle());
        book.setYear(model.getYear());
        book.setIsbn(model.getIsbn());
        book.setEdition(model.getEdition());

        Author author = authorRepository.findById(UUID.fromString(model.getAuthor_id()))
                .orElseThrow(() -> new RuntimeException("Author not found"));

        book.setAuthor(author);
    }


}
