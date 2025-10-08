package com.example.library.controller;

import com.example.library.dto.author.AuthorDto;
import com.example.library.dto.author.AuthorInsertRequest;
import com.example.library.dto.author.AuthorUpdateRequest;
import com.example.library.entity.Author;
import com.example.library.service.AuthorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/List")
    public List<AuthorDto> list() {
        return  authorService.getAllAuthors();
    }

    @PostMapping("/Insert")
    public AuthorDto create(@RequestBody AuthorInsertRequest author) {
        return  authorService.saveAuthor(author);
    }

    @PostMapping("/Update")
    public AuthorDto update(@RequestBody AuthorUpdateRequest authorUpdateRequest) {
        return  authorService.updateAuthor(authorUpdateRequest);
    }
}
