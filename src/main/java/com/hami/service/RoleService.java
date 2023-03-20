package com.hami.service;

import com.hami.entity.Role;

public interface RoleService {

    public Role updateRole(Role role);
    public void deleteRoleById(Long roleId);

}
