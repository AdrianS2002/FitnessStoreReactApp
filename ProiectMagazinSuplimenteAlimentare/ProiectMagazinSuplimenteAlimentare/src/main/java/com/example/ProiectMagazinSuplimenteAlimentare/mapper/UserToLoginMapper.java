package com.example.ProiectMagazinSuplimenteAlimentare.mapper;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.RoleDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.SuccessfulLoginDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.model.User;

import java.util.stream.Collectors;

/**
 * UserToLoginMapper class is used to convert the User object to SuccessfulLoginDTO object.

 */
public class UserToLoginMapper {
    public static SuccessfulLoginDTO mapUserToDTO(User user) {
        if (user == null) {
            return null;
        }
        return SuccessfulLoginDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(user.getRoles().stream().map(RoleMapper::toDTO)
                        .collect(Collectors.toList())
                        .toArray(new RoleDTO[0])
                )
                .build();
    }
}
