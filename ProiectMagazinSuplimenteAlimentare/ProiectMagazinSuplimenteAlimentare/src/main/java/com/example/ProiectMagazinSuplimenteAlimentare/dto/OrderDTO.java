package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import com.example.ProiectMagazinSuplimenteAlimentare.model.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *  OrderDTO class is used to transfer the order data from the server to the client.

 */
@Getter
@Setter
@Builder
public class OrderDTO {
    private Long id;
    private Double totalPrice;
    private Long orderNumber;
    private List<Product>products;
}
