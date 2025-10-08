package com.example.library.repository;

import com.example.library.entity.Book;
import com.example.library.entity.BookUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookUserRepository   extends JpaRepository<BookUser, UUID> {

    Optional<BookUser> findById(UUID id);

    BookUser getByBook(Book book);

    List<BookUser> findByUser_Id(UUID userId);
}
