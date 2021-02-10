package com.dev.spring.dao;

import com.dev.spring.model.User;
import java.util.List;

public interface UserDao {
    void add(User user);

    User get(Long id);

    List<User> listUsers();
}
