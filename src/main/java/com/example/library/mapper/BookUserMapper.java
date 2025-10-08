package com.example.library.mapper;

import com.example.library.dto.bookUser.BookUserDto;
import com.example.library.entity.BookUser;
import org.springframework.stereotype.Component;

@Component
public class BookUserMapper {

    private final BookMapper bookMapper;
    private final UserMapper userMapper;

    public BookUserMapper(BookMapper bookMapper, UserMapper userMapper) {
        this.bookMapper = bookMapper;
        this.userMapper = userMapper;
    }

    public BookUserDto toBookUser(BookUser bookUser) {
        BookUserDto bookUserDto = new BookUserDto();

        bookUserDto.setId(bookUser.getId());
        bookUserDto.setBook(bookMapper.toBookDto(bookUser.getBook()));
        bookUserDto.setUser(userMapper.toUserDto(bookUser.getUser()));
        bookUserDto.setReturned(bookUser.isReturned());
        bookUserDto.setBorrowedDate(bookUser.getBorrowedDate());
        bookUserDto.setDueDate(bookUser.getDueDate());

        return bookUserDto;
    }
}
