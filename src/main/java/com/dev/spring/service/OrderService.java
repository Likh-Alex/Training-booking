package com.dev.spring.service;

import com.dev.spring.model.Order;
import com.dev.spring.model.ShoppingCart;
import com.dev.spring.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
