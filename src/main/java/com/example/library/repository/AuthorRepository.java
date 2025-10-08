package com.example.library.repository;

import com.example.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository  extends JpaRepository<Author, UUID> {

    Author getFirstByName(String name);
}
