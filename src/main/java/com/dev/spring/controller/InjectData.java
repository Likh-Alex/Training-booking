package com.dev.spring.controller;

import com.dev.spring.model.Role;
import com.dev.spring.model.User;
import com.dev.spring.service.RoleService;
import com.dev.spring.service.UserService;
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
        admin.setEmail("bob");
        admin.setPassword("1234");
        userService.add(admin);
    }
}
