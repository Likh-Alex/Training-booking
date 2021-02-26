package com.dev.fitbooking.service.impl;

import com.dev.fitbooking.dao.RoleDao;
import com.dev.fitbooking.model.Role;
import com.dev.fitbooking.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void add(Role role) {
        roleDao.add(role);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleDao.getRoleByName(roleName).orElseThrow(()
                -> new RuntimeException("Can not get role: " + roleName));
    }
}
