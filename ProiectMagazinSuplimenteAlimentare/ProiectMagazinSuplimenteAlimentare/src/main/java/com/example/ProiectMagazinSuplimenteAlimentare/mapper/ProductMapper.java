package com.example.ProiectMagazinSuplimenteAlimentare.mapper;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.ProductCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.ProductDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Product;

/**
 * ProductMapper class is used to convert the ProductDTO and ProductCreationDTO objects to Product objects and vice versa.

 */
public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .image(product.getImage())
                .build();
    }

    public static Product toEntity(ProductDTO productCreationDTO) {
        return Product.builder()
                .name(productCreationDTO.getName())
                .description(productCreationDTO.getDescription())
                .price(productCreationDTO.getPrice())
                .quantity(productCreationDTO.getQuantity())
                .id(productCreationDTO.getId())
                .image(productCreationDTO.getImage())
                .build();
    }
    public static Product toCreationEntity(ProductCreationDTO productCreationDTO) {
        return Product.builder()
                .name(productCreationDTO.getName())
                .description(productCreationDTO.getDescription())
                .price(productCreationDTO.getPrice())
                .quantity(productCreationDTO.getQuantity())
                .image(productCreationDTO.getImage())
                .build();
    }

    public static ProductCreationDTO toCreationDTO(Product product) {
        return ProductCreationDTO.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .image(product.getImage())
                .build();
    }

}
