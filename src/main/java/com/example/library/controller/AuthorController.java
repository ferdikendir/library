package com.example.library.controller;

import com.example.library.dto.author.*;
import com.example.library.service.AuthorService;
import org.springframework.web.bind.annotation.*;

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
