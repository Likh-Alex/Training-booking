package com.dev.fitbooking.service;

import com.dev.fitbooking.model.Order;
import com.dev.fitbooking.model.ShoppingCart;
import com.dev.fitbooking.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
