package com.dev.spring.dao;

import com.dev.spring.model.Order;
import com.dev.spring.model.User;
import java.util.List;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrderHistory(User user);
}
