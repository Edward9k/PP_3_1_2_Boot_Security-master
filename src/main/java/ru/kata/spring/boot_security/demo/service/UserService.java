package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    void updateUser(User user);
    void deleteUserById(int id);
    Optional<User> findByUsername(String username);
}
