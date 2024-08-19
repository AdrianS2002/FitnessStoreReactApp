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
public class ProductCreationDTO {
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private String image;
}
