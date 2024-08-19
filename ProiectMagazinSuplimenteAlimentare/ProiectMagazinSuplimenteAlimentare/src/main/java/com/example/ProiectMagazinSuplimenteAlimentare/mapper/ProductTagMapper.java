package com.example.ProiectMagazinSuplimenteAlimentare.mapper;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.TagCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.TagDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.model.ProductTag;

/**
 * ProductTagMapper class is used to convert the TagDTO and TagCreationDTO objects to ProductTag objects and vice versa.

 */
public class ProductTagMapper {
    public static ProductTag toEntity(TagDTO dto){
        return ProductTag.builder()
                .id(dto.getId())
                .name(dto.getName())
                .products(dto.getProducts())
                .build();
    }

    public static TagDTO toDto(ProductTag productTag){
        return TagDTO.builder()
                .id(productTag.getId())
                .name(productTag.getName())
                .products(productTag.getProducts())
                .build();
    }

    public static ProductTag toCreationEntity(TagCreationDTO dto){
        return ProductTag.builder()
                .name(dto.getName())
                .products(dto.getProducts())
                .build();
    }

    public static TagCreationDTO toCreationDto(ProductTag productTag){
        return TagCreationDTO.builder()
                .name(productTag.getName())
                .products(productTag.getProducts())
                .build();
    }
}
