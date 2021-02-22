package com.dev.spring.controller;

import com.dev.spring.model.Role;
import com.dev.spring.model.User;
import com.dev.spring.service.RoleService;
import com.dev.spring.service.UserService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class InjectData {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InjectData(UserService userService, RoleService roleService,
                      PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void injectRoles() {
        Role adminRole = new Role();
        Role userRole = new Role();
        adminRole.setRoleType(Role.RoleName.ADMIN);
        userRole.setRoleType(Role.RoleName.USER);
        roleService.add(adminRole);
        roleService.add(userRole);

        User admin = new User();
        admin.setRole(adminRole);
        admin.setEmail("bob");
        admin.setPassword(passwordEncoder.encode("1234"));
        userService.add(admin);
    }
}
