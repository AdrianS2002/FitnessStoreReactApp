package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *  OrderUserDTO class is used to transfer the order data from the server to the client.
 */
@Getter
@Setter
@NoArgsConstructor
public class OrderUserDTO {
    private Long orderId;
    private Long UserId;

}
