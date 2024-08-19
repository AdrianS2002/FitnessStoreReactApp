package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
/**
 *  RoleCreationDTO class is used to transfer the role data from the server to the client.
 */
@Getter
@Setter
@Builder
public class RoleCreationDTO {
    private String role;
}
