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
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/list")
    public List<AuthorDto> list() {
        return  authorService.getAllAuthors();
    }

    @PostMapping("/insert")
    public AuthorDto create(@RequestBody AuthorInsertRequest author) {
        return  authorService.saveAuthor(author);
    }

    @PostMapping("/update")
    public AuthorDto update(@RequestBody AuthorUpdateRequest authorUpdateRequest) {
        return  authorService.updateAuthor(authorUpdateRequest);
    }
}
