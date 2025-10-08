package com.example.library.mapper;

import com.example.library.dto.author.AuthorDto;
import com.example.library.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorDto toAuthorDto(Author author) {
        AuthorDto authorDto = new AuthorDto();

        authorDto.setId(author.getId());
        authorDto.setName(author.getName());
        authorDto.setSurname(author.getSurname());

        return authorDto;
    }
}
