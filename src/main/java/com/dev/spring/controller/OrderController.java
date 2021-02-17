package com.dev.spring.controller;

import com.dev.spring.dto.response.OrderResponseDto;
import com.dev.spring.model.Order;
import com.dev.spring.service.OrderService;
import com.dev.spring.service.ShoppingCartService;
import com.dev.spring.service.UserService;
import com.dev.spring.service.mapper.OrderMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public OrderController(UserService userService,
                           OrderService orderService,
                           OrderMapper orderMapper,
                           ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public List<OrderResponseDto> getOrdersHistory(@RequestParam Long userId) {
        List<Order> ordersHistory = orderService.getOrdersHistory(userService.get(userId));
        return ordersHistory.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/complete")
    public void completeOrder(@RequestParam Long userId) {
        orderService.completeOrder(shoppingCartService
                .getByUser(userService.get(userId)));
    }
}
