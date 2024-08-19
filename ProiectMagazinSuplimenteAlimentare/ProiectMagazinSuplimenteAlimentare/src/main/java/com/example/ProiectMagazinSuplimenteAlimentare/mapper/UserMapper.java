package com.example.ProiectMagazinSuplimenteAlimentare.mapper;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.UserCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.UserDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.model.User;

/**
 * UserMapper class is used to convert the UserDTO and UserCreationDTO objects to User objects and vice versa.
 */
public class UserMapper {

    public static User toEntity(UserDTO dto){
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .telephone(dto.getTelephone())
                .roles(dto.getRoles())
                .orders(dto.getOrders())
                .flag(dto.getFlag())
                .build();
    }

    public static UserDTO toDto(User user){
        return UserDTO.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .telephone(user.getTelephone())
                .id(user.getId())
                .roles(user.getRoles())
                .orders(user.getOrders())
                .flag(user.getFlag())
                .build();
    }



    public static User toCreationEntity(UserCreationDTO dto){
        return User.builder()
                .name(dto.getName())
                .username(dto.getUsername())
                .telephone(dto.getTelephone())
                .password(dto.getPassword())
                .flag(dto.getFlag())
                .build();
    }

    public static UserCreationDTO toCreationDto(User user){
        return UserCreationDTO.builder()
                .name(user.getName())
                .telephone(user.getTelephone())
                .username(user.getUsername())
                .password(user.getPassword())
                .flag(user.getFlag())
                .build();
    }

}
