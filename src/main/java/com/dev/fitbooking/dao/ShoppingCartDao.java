package com.dev.fitbooking.dao;

import com.dev.fitbooking.model.ShoppingCart;
import com.dev.fitbooking.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
