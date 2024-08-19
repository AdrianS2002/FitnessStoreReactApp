package com.example.ProiectMagazinSuplimenteAlimentare.service.role;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.RoleCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.RoleDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.exceptions.ApiExceptionResponse;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * RoleService interface is used to create the methods that will be implemented in the RoleServiceImpl class.
 */

@Component
public interface RoleService  {
    List<Role> findAll();
    Role findRoleById(Integer id);
    Role findRoleByRole(String role) throws ApiExceptionResponse;
    RoleDTO saveRole(RoleCreationDTO role);
    boolean deleteRole(Integer id);
    RoleDTO updateRole(RoleDTO role);
}
