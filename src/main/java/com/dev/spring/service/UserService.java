package com.dev.spring.service;

import com.dev.spring.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User add(User user);

    User get(Long id);

    List<User> listUsers();

    Optional<User> findByEmail(String email);
}
