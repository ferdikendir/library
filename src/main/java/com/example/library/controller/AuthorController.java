package com.example.library.controller;

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
    public List<Author> list() {
        return  authorService.getAllAuthors();
    }

    @PostMapping("/insert")
    public Author create(@RequestBody Author author) {
        return  authorService.saveAuthor(author);
    }

    @PostMapping("/update")
    public Author update(@RequestBody Author author) {
        return  authorService.updateAuthor(author);
    }
}
