package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import com.example.ProiectMagazinSuplimenteAlimentare.model.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
/**
 *  TagCreationDTO class is used to transfer the tag data from the server to the client.
 */
@Getter
@Setter
@Builder
public class TagCreationDTO {
    private String name;
    private List<Product> products;
}
