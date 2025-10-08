package com.example.library.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
    @Id
    @GeneratedValue()
    @Column(columnDefinition = "uuid", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

}
