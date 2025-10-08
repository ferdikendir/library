package com.example.library.data;

import com.example.library.entity.*;
import com.example.library.repository.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder,AuthorRepository authorRepository,BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        addUsers();
        addAuthor();
        addBooks();
    }

    private void addUsers() {
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setName("admin");
            admin.setSurname("admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setUsername("admin");
            admin.setRole("admin");

            User user = new User();
            user.setName("user");
            user.setSurname("user");
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("654321"));
            user.setRole("user");

            userRepository.save(admin);
            userRepository.save(user);

            System.out.println("Mock users inserted into database");
        } else {
            System.out.println("Users already exist, skipping mock data insertion");
        }
    }

    private void addAuthor() {
        if (authorRepository.count() == 0) {
            Author author = new Author();
            author.setName("Dan");
            author.setSurname("Brown");

            authorRepository.save(author);

        } else {

            System.out.println("Author already exist, skipping mock data insertion");
        }
    }

    private void addBooks() {
        if (bookRepository.count() == 0) {
            Book book = new Book();
            book.setIsbn("978-0-7432-7356-8");
            book.setTitle("Digital Fortress");
            book.setEdition("1st");
            book.setYear("1999");

            Author author = authorRepository.getFirstByName("Dan");

            book.setAuthor(author);
            bookRepository.save(book);
        } else {
            System.out.println("Books already exist, skipping mock data insertion");
        }
    }
}

