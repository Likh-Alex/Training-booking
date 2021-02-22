package com.dev.spring.security.impl;

import static org.springframework.security.core.userdetails.User.builder;

import com.dev.spring.model.User;
import com.dev.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username).orElseThrow(()
                -> new UsernameNotFoundException("Can not get user with username: " + username));
        UserBuilder userBuilder = builder();
        userBuilder.username(username);
        userBuilder.password(user.getPassword());
        userBuilder.roles(user.getRole().toString());
        return userBuilder.build();
    }
}
