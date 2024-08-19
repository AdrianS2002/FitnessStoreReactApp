package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * OrderProductDTO class is used to transfer the order-product data from the server to the client.

 */
@Getter
@Setter
@NoArgsConstructor
public class OrderProductDTO {
    private Long orderId;
    private Long productId;
}
