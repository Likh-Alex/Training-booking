package com.dev.spring.service;

import com.dev.spring.model.MovieSession;
import com.dev.spring.model.ShoppingCart;
import com.dev.spring.model.User;

public interface ShoppingCartService {
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
