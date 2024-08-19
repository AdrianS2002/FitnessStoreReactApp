package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * AuthDTO class is used to transfer the authentication data from the client to the server.
 */
@Getter
@Setter
public class AuthDTO {

    private String username;
    private String password;
}
