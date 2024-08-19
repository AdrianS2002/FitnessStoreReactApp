package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *  RoleUserAtributtionDTO class is used to transfer the role user data from the server to the client.
 */
@Getter
@Setter
@NoArgsConstructor
public class RoleUserAtributtionDTO {
    private int roleId;
    private Long userId;
}
