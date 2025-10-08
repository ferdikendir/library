package com.example.library.service;

import com.example.library.entity.User;
import com.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> list() {
        return userRepository.list();
    }

    public User update(User user) {

        User oldUser = userRepository.findById(user.getId()).get();

        oldUser.setName(user.getName());
        oldUser.setSurname(user.getSurname());

        return userRepository.save(oldUser);

    }
}
