package com.example.ProiectMagazinSuplimenteAlimentare.mapper;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.RoleCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.RoleDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Role;

import java.util.List;

/**
 * RoleMapper class is used to convert the RoleDTO and RoleCreationDTO objects to Role objects and vice versa.

 */
public class RoleMapper {

    public static RoleDTO toDTO(Role role) {
        return RoleDTO.builder()
                .role(role.getRole())
                .id(role.getId())
                .build();
    }



    public static Role toEntity(RoleDTO roleDTO) {
        return Role.builder()
                .role(roleDTO.getRole())
                .id(roleDTO.getId())
                .build();
    }

    public static Role toCreationEntity(RoleCreationDTO roleCreationDTO) {
        return Role.builder()
                .role(roleCreationDTO.getRole())
                .build();
    }



    public static RoleCreationDTO toCreationDTO(Role role) {
        return RoleCreationDTO.builder()
                .role(role.getRole())
                .build();
    }

}
