package com.dev.spring.controller;

import com.dev.spring.dto.response.ShoppingCartResponseDto;
import com.dev.spring.model.MovieSession;
import com.dev.spring.model.ShoppingCart;
import com.dev.spring.model.User;
import com.dev.spring.service.MovieSessionService;
import com.dev.spring.service.ShoppingCartService;
import com.dev.spring.service.UserService;
import com.dev.spring.service.mapper.ShoppingCartMapper;
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
    private final MovieSessionService movieSessionService;
    private final UserService userService;
    private final ShoppingCartMapper shoppingCartMapper;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  MovieSessionService movieSessionService,
                                  UserService userService,
                                  ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartService = shoppingCartService;
        this.movieSessionService = movieSessionService;
        this.userService = userService;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(Authentication authentication) {
        UserDetails details = (UserDetails) authentication.getPrincipal();
        String userEmail = details.getUsername();
        User user = userService.findByEmail(userEmail).orElseThrow(()
                -> new RuntimeException("No user with email: " + userEmail));
        ShoppingCart shoppingCart = shoppingCartService.getByUser(userService.get(user.getId()));
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @PostMapping("/movie-sessions")
    public void add(Authentication authentication, @RequestParam Long movieSessionId) {
        UserDetails details = (UserDetails) authentication.getPrincipal();
        String userEmail = details.getUsername();
        User user = userService.findByEmail(userEmail).orElseThrow(()
                -> new RuntimeException("No user with email: " + userEmail));
        MovieSession movieSession = movieSessionService.get(movieSessionId);
        shoppingCartService.addSession(movieSession, user);
    }
}
