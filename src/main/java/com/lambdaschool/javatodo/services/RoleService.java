package com.lambdaschool.javatodo.services;

import com.lambdaschool.javatodo.models.Role;

import java.util.List;

public interface RoleService
{
    List<Role> findAll();

    Role findRoleById(long id);

    void delete(long id);

    Role save(Role role);
}