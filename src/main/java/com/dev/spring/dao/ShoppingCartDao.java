package com.dev.spring.dao;

import com.dev.spring.model.ShoppingCart;
import com.dev.spring.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
