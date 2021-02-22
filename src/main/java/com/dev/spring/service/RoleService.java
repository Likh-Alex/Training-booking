package com.dev.spring.service;

import com.dev.spring.model.Role;
import org.springframework.stereotype.Component;

@Component
public interface RoleService {
    void add(Role role);

    Role getRoleByName(String roleName);
}
