package com.example.ProiectMagazinSuplimenteAlimentare.mapper;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.RoleDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.SuccessfulLoginDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.SuccessfulLogoutDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.model.User;

import java.util.stream.Collectors;

public class UserToLogoutMapper {
    public static SuccessfulLogoutDTO mapUserToDTO(User user) {
        if (user == null) {
            return null;
        }
        return SuccessfulLogoutDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(user.getRoles().stream().map(RoleMapper::toDTO)
                        .collect(Collectors.toList())
                        .toArray(new RoleDTO[0])
                )
                .build();
    }
}
