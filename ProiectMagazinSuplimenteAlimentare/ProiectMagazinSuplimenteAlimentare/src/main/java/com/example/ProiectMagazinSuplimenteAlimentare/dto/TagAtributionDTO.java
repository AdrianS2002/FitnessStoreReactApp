package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *  TagAtributionDTO class is used to transfer the tag atribution data from the server to the client.
 */
@Getter
@Setter
@NoArgsConstructor
public class TagAtributionDTO {
    private Long productTagId;
    private Long productId;
}
