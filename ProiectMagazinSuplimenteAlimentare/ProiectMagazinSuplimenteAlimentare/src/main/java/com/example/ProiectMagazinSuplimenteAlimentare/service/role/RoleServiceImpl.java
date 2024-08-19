package com.example.ProiectMagazinSuplimenteAlimentare.service.role;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.RoleCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.RoleDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.exceptions.ApiExceptionResponse;
import com.example.ProiectMagazinSuplimenteAlimentare.mapper.RoleMapper;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Role;
import com.example.ProiectMagazinSuplimenteAlimentare.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RoleServiceImpl class implements the methods defined in the RoleService interface.

 */
@Service
public class RoleServiceImpl implements RoleService  {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public Role findRoleById(Integer id) {
        return roleRepository.findById(id).orElseThrow();
    }

    @Override
    public Role findRoleByRole(String role) throws ApiExceptionResponse {
        Role role1 = roleRepository.findByRole(role);
        if (role1 == null) {
            throw ApiExceptionResponse.builder()
                    .errors(List.of("No role with role " + role))
                    .message("Entity not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return role1;
    }

    @Override
    public RoleDTO saveRole(RoleCreationDTO dto) {
        Role entity = RoleMapper.toCreationEntity(dto);
        entity = roleRepository.save(entity);
        return RoleMapper.toDTO(entity);

    }

    @Override
    public boolean deleteRole(Integer id) {
        roleRepository.deleteById(id);
        return roleRepository.findById(id).isEmpty();

    }

    @Override
    public RoleDTO updateRole(RoleDTO dto) {
        Role role = RoleMapper.toEntity(dto);
        boolean exists = roleRepository.findById(role.getId()).isPresent();
        if(exists) {
            role = roleRepository.save(role);
            return RoleMapper.toDTO(role);
        }
        return null;
    }
}
