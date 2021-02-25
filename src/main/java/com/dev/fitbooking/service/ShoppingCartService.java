package com.dev.fitbooking.service;

import com.dev.fitbooking.model.ShoppingCart;
import com.dev.fitbooking.model.User;
import com.dev.fitbooking.model.WorkoutSession;

public interface ShoppingCartService {
    void addSession(WorkoutSession workoutSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
