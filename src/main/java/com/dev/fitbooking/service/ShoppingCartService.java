package com.dev.fitbooking.service;

import com.dev.fitbooking.model.WorkoutSession;
import com.dev.fitbooking.model.ShoppingCart;
import com.dev.fitbooking.model.User;

public interface ShoppingCartService {
    void addSession(WorkoutSession workoutSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
