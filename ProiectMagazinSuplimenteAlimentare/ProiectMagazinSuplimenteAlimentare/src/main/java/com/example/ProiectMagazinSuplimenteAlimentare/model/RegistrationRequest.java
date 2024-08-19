package com.example.ProiectMagazinSuplimenteAlimentare.model;

import lombok.Data;

/**
 * RegistrationRequest class is used to create a registration request object with the following attributes: username, password, firstName, lastName, emailAddress.

 */
@Data
public class RegistrationRequest {
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String emailAddress;
}