package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import com.example.ProiectMagazinSuplimenteAlimentare.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
/**
 * OrderCreationDTO class is used to transfer the order creation data from the client to the server.
 */
@Getter
@Setter
@Builder
public class TagDTO {
    private Long id;
    @Size(min = 3, max = 50)
    @NotBlank
    private String name;
    private List<Product> products;
}
