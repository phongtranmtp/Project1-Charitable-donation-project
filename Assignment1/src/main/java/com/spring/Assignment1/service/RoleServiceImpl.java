package com.spring.Assignment1.service;

import com.spring.Assignment1.dao.impl.RoleDAO;
import com.spring.Assignment1.entity.Role;
import com.spring.Assignment1.model.RoleDTO;
import com.spring.Assignment1.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDAO roleDAO;

    @Override
    public List<RoleDTO> getRoles() {
        List<Role> roles = roleDAO.getRoles();
        List<RoleDTO> roleDTOs = new ArrayList<>();
        for (Role role : roles) {
            roleDTOs.add(convetRoleDTOs(role));
        }
        return roleDTOs;
    }

    public RoleDTO convetRoleDTOs(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setRoleName(role.getRoleName());
        roleDTO.setUsers(role.getUsers());

        return roleDTO;
    }

}
