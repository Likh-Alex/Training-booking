package com.dev.spring.security;

import com.dev.spring.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
