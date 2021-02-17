package com.dev.spring.dao;

import com.dev.spring.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    User add(User user);

    Optional<User> get(Long id);

    List<User> listUsers();

    Optional<User> findByEmail(String email);
}
