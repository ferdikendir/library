package com.example.library.mapper;

import com.example.library.dto.author.AuthorDto;
import com.example.library.dto.book.BookDto;
import com.example.library.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDto toBookDto(Book book) {
        BookDto bookDto = new BookDto();

        bookDto.setId(book.getId());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setTitle(book.getTitle());
        bookDto.setEdition(book.getEdition());
        bookDto.setYear(book.getYear());

        AuthorDto authorDto = new AuthorDto();

        authorDto.setId(book.getAuthor().getId());
        authorDto.setName(book.getAuthor().getName());
        authorDto.setSurname(book.getAuthor().getSurname());

        bookDto.setAuthor(authorDto);

        return bookDto;
    }

}
