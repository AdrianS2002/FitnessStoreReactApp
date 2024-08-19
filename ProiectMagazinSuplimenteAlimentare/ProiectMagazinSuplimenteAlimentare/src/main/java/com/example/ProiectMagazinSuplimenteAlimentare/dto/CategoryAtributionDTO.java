package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CategoryAtributionDTO class is used to transfer the category atribution data from the client to the server.

 */
@Getter
@Setter
@NoArgsConstructor
public class CategoryAtributionDTO {
    private Long productCategoryId;
    private Long productId;
}
