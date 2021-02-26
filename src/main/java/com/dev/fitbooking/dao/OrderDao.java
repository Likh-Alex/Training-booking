package com.dev.fitbooking.dao;

import com.dev.fitbooking.model.Order;
import com.dev.fitbooking.model.User;
import java.util.List;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrderHistory(User user);
}
