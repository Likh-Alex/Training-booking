package com.dev.fitbooking.controller;

import com.dev.fitbooking.model.Role;
import com.dev.fitbooking.model.User;
import com.dev.fitbooking.service.RoleService;
import com.dev.fitbooking.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InjectData {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public InjectData(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
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
        admin.setRoles(Set.of(adminRole));
        admin.setEmail("admin");
        admin.setPassword("1234");
        userService.add(admin);
    }
}
