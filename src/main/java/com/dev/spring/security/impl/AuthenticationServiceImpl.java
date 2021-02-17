package com.dev.spring.security.impl;

import static com.dev.spring.security.HashUtil.hashPassword;

import com.dev.spring.exception.AuthenticationException;
import com.dev.spring.model.User;
import com.dev.spring.security.AuthenticationService;
import com.dev.spring.service.ShoppingCartService;
import com.dev.spring.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()
                && user.get().getPassword().equals(hashPassword(password, user.get().getSalt()))) {
            return user.get();
        }
        throw new AuthenticationException("Email or password is incorrect");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        User userFromDb = userService.add(user);
        shoppingCartService.registerNewShoppingCart(userFromDb);
        return userFromDb;
    }
}
