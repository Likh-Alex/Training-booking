package com.dev.spring.dao;

import com.dev.spring.model.Role;
import java.util.Optional;

public interface RoleDao {
    void add(Role role);

    Optional<Role> getRoleByName(String roleName);
}
