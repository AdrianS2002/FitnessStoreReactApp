package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
/**
 *  ProductCreationDTO class is used to transfer the product data from the server to the client.
 */
@Getter
@Setter
@Builder
public class RoleDTO {
    private Integer id;
    private String role;
}
