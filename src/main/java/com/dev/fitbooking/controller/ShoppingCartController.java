package com.dev.fitbooking.controller;

import com.dev.fitbooking.dto.response.ShoppingCartResponseDto;
import com.dev.fitbooking.model.ShoppingCart;
import com.dev.fitbooking.model.User;
import com.dev.fitbooking.model.WorkoutSession;
import com.dev.fitbooking.service.ShoppingCartService;
import com.dev.fitbooking.service.UserService;
import com.dev.fitbooking.service.WorkoutSessionService;
import com.dev.fitbooking.service.mapper.ShoppingCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final WorkoutSessionService workoutSessionService;
    private final UserService userService;
    private final ShoppingCartMapper shoppingCartMapper;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  WorkoutSessionService workoutSessionService,
                                  UserService userService,
                                  ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartService = shoppingCartService;
        this.workoutSessionService = workoutSessionService;
        this.userService = userService;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(Authentication authentication) {
        UserDetails details = (UserDetails) authentication.getPrincipal();
        String userEmail = details.getUsername();
        User user = userService.findByEmail(userEmail).orElseThrow(()
                -> new RuntimeException("No user with email: " + userEmail));
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @PostMapping("/workout-sessions")
    public void add(Authentication authentication, @RequestParam Long workoutSessionId) {
        UserDetails details = (UserDetails) authentication.getPrincipal();
        String userEmail = details.getUsername();
        User user = userService.findByEmail(userEmail).orElseThrow(()
                -> new RuntimeException("No user with email: " + userEmail));
        WorkoutSession workoutSession = workoutSessionService.get(workoutSessionId);
        shoppingCartService.addSession(workoutSession, user);
    }
}
