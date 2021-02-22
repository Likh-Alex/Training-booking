package com.dev.spring.security.impl;

import com.dev.spring.model.Role;
import com.dev.spring.model.User;
import com.dev.spring.security.AuthenticationService;
import com.dev.spring.service.RoleService;
import com.dev.spring.service.ShoppingCartService;
import com.dev.spring.service.UserService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final RoleService roleService;

    @Autowired
    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService,
                                     RoleService roleService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.roleService = roleService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(Set.of(roleService.getRoleByName(String.valueOf(Role.RoleName.USER))));
        User userFromDb = userService.add(user);
        shoppingCartService.registerNewShoppingCart(userFromDb);
        return userFromDb;
    }
}
