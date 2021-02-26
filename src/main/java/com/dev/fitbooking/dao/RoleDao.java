package com.dev.fitbooking.dao;

import com.dev.fitbooking.model.Role;
import java.util.Optional;

public interface RoleDao {
    void add(Role role);

    Optional<Role> getRoleByName(String roleName);
}
