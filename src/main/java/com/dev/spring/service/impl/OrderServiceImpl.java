package com.dev.spring.service.impl;

import com.dev.spring.dao.OrderDao;
import com.dev.spring.model.Order;
import com.dev.spring.model.ShoppingCart;
import com.dev.spring.model.User;
import com.dev.spring.service.OrderService;
import com.dev.spring.service.ShoppingCartService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao,
                            ShoppingCartService shoppingCartService) {
        this.orderDao = orderDao;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order newOrder = new Order();
        newOrder.setUser(shoppingCart.getUser());
        newOrder.setTickets(new ArrayList<>(shoppingCart.getTickets()));
        newOrder.setOrderDate(LocalDateTime.now());
        orderDao.add(newOrder);
        shoppingCartService.clear(shoppingCart);
        return newOrder;
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
