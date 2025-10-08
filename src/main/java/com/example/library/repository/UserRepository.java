package com.example.library.repository;

import com.example.library.entity.User;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.role = 'user'")
    List<User> list();
}
