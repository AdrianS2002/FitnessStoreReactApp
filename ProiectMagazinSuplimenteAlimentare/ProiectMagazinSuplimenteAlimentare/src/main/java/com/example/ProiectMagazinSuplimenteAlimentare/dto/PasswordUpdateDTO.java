package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordUpdateDTO {

    @Email
    private String username;

    private String newPassword;

}
