package com.example.library.repository;

import com.example.library.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BookUserRepository   extends JpaRepository<BookUser, UUID> {

    List<BookUser> findByUser_Id(UUID userId);

    @Query("SELECT u FROM BookUser u WHERE u.returned = false and u.book = :book ")
    BookUser borrowBookNow(@Param("book") Book book);
}
