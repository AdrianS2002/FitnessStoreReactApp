package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import com.example.ProiectMagazinSuplimenteAlimentare.model.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
/**
 *  ProductCategoryCreationDTO class is used to transfer the product category data from the server to the client.
 */
@Getter
@Setter
@Builder
public class ProductCategoryCreationDTO {
    private String name;
    private List<Product>products;
}
