package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import com.example.ProiectMagazinSuplimenteAlimentare.model.Product;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *  ProductCategoryDTO class is used to transfer the product category data from the server to the client.

 */
@Getter
@Setter
@Builder
public class ProductCategoryDTO {
    private Long id;
    @Size(min = 3, max = 50)
    private String name;
    private List<Product>products;
}
