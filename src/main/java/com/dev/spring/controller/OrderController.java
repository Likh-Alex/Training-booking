package com.dev.spring.controller;

import com.dev.spring.dto.response.OrderResponseDto;
import com.dev.spring.model.Order;
import com.dev.spring.model.User;
import com.dev.spring.service.OrderService;
import com.dev.spring.service.ShoppingCartService;
import com.dev.spring.service.UserService;
import com.dev.spring.service.mapper.OrderMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<OrderResponseDto> getOrdersHistory(Authentication authentication) {
        UserDetails details = (UserDetails) authentication.getPrincipal();
        String userEmail = details.getUsername();
        User user = userService.findByEmail(userEmail).orElseThrow(()
                -> new RuntimeException("No user with email: " + userEmail));
        List<Order> ordersHistory = orderService.getOrdersHistory(user);
        return ordersHistory.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/complete")
    public void completeOrder(Authentication authentication) {
        UserDetails details = (UserDetails) authentication.getPrincipal();
        String userEmail = details.getUsername();
        User user = userService.findByEmail(userEmail).orElseThrow(()
                -> new RuntimeException("No user with email: " + userEmail));
        orderService.completeOrder(shoppingCartService
                .getByUser(user));
    }
}
