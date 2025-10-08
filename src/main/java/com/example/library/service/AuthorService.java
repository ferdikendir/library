package com.example.library.service;

import com.example.library.dto.author.*;
import com.example.library.entity.Author;
import com.example.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthorById(UUID id) {
        return authorRepository.findById(id).orElse(null);
    }

    public List<AuthorDto> getAllAuthors() {
        List<AuthorDto> authors = new ArrayList<AuthorDto>();

        for (Author author : authorRepository.findAll()) {
            AuthorDto authorDto = new AuthorDto();

            authorDto.setId(author.getId());
            authorDto.setName(author.getName());
            authorDto.setSurname(author.getSurname());

            authors.add(authorDto);
        }

        return authors;
    }

    public AuthorDto saveAuthor(AuthorInsertRequest authorInsertRequest) {
        Author author = new Author();

        author.setName(authorInsertRequest.getName());
        author.setSurname(authorInsertRequest.getSurname());

        Author addedAuthor = authorRepository.save(author);

        AuthorDto authorDto = new AuthorDto();

        authorDto.setId(addedAuthor.getId());
        authorDto.setName(addedAuthor.getName());
        authorDto.setSurname(addedAuthor.getSurname());

        return authorDto;
    }

    public AuthorDto updateAuthor(AuthorUpdateRequest authorUpdateRequest) {
        Author author = getAuthorById(authorUpdateRequest.getId());

        author.setName(authorUpdateRequest.getName());
        author.setSurname(authorUpdateRequest.getSurname());
        Author updatedAuthor =   authorRepository.save(author);

        AuthorDto authorDto = new AuthorDto();

        authorDto.setId(updatedAuthor.getId());
        authorDto.setName(updatedAuthor.getName());
        authorDto.setSurname(updatedAuthor.getSurname());

        return authorDto;
    }

}
