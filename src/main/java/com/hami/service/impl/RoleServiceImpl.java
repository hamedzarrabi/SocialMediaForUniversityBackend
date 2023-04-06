package com.hami.service.impl;

import com.hami.entity.Role;
import com.hami.repository.RoleRepository;
import com.hami.repository.UserRepository;
import com.hami.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired RoleRepository roleRepository;

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRoleById(Long roleId) {
        roleRepository.deleteById(roleId);
    }
}
