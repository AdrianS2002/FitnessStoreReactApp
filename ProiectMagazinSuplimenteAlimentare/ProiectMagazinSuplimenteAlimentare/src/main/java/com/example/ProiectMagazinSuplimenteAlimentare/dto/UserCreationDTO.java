package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
/**
 *  UserCreationDTO class is used to transfer the user data from the server to the client.
 */
@Getter
@Setter
@Builder
public class UserCreationDTO {
    private String name;
    private String username;
    private String password;
    private String telephone;
    private Boolean flag;
}
