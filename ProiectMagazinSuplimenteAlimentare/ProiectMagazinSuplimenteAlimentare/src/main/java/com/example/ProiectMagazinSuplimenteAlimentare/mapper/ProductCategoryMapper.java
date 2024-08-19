package com.example.ProiectMagazinSuplimenteAlimentare.mapper;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.ProductCategoryCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.ProductCategoryDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.model.ProductCategory;

/**
 * ProductCategoryMapper class is used to convert the ProductCategoryDTO and ProductCategoryCreationDTO objects to ProductCategory objects and vice versa.
 */
public class ProductCategoryMapper {
    public static ProductCategory toEntity(ProductCategoryDTO dto){
        return ProductCategory.builder()
                .id(dto.getId())
                .name(dto.getName())
                .products(dto.getProducts())
                .build();
    }

    public static ProductCategoryDTO toDto(ProductCategory productCategory){
        return ProductCategoryDTO.builder()
                .id(productCategory.getId())
                .name(productCategory.getName())
                .products(productCategory.getProducts())
                .build();
    }

    public static ProductCategory toCreationEntity(ProductCategoryCreationDTO dto){
        return ProductCategory.builder()
                .name(dto.getName())
                .products(dto.getProducts())
                .build();
    }



    public static ProductCategoryCreationDTO toCreationDto(ProductCategory productCategory){
        return ProductCategoryCreationDTO.builder()
                .name(productCategory.getName())
                .products(productCategory.getProducts())
                .build();
    }
}
