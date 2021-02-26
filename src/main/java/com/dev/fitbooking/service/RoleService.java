package com.dev.fitbooking.service;

import com.dev.fitbooking.model.Role;

public interface RoleService {
    void add(Role role);

    Role getRoleByName(String roleName);
}
