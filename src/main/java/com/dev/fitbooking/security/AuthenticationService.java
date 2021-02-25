package com.dev.fitbooking.security;

import com.dev.fitbooking.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
