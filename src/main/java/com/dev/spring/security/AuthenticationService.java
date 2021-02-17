package com.dev.spring.security;

import com.dev.spring.exception.AuthenticationException;
import com.dev.spring.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
