package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SuccessfulLogoutDTO {
    private Long id;
    private String username;
    private RoleDTO[] roles;
}
