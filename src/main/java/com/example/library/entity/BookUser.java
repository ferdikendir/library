package com.example.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "book_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookUser {

    @Id
    @GeneratedValue()
    @Column(columnDefinition = "uuid", updatable = false, nullable = false, unique = true)
    private UUID id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private LocalDate borrowedDate;

    @Column( nullable = false)
    private LocalDate dueDate;


}
