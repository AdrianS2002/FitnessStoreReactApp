package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import com.example.ProiectMagazinSuplimenteAlimentare.model.Product;
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
public class OrderCreationDTO {
    private Double totalPrice;
    private Long orderNumber;
    private List<Product> products;
}
