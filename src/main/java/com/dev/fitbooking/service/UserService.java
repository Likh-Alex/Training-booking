package com.dev.fitbooking.service;

import com.dev.fitbooking.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User add(User user);

    User get(Long id);

    List<User> listUsers();

    Optional<User> findByEmail(String email);
}
