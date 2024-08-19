package com.example.ProiectMagazinSuplimenteAlimentare.mapper;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.OrderCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.OrderDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Order;
/**
 * OrderMapper class is used to convert the OrderDTO and OrderCreationDTO objects to Order objects and vice versa.
 *
 */
public class OrderMapper {

    public static Order toEntity(OrderDTO dto){
        return Order.builder()
                .id(dto.getId())
                .totalPrice(dto.getTotalPrice())
                .orderNumber(dto.getOrderNumber())
                .products(dto.getProducts())
                .build();
    }



    public static OrderDTO toDto(Order order){
        return OrderDTO.builder()
                .id(order.getId())
                .totalPrice(order.getTotalPrice())
                .orderNumber(order.getOrderNumber())
                .products(order.getProducts())
                .build();
    }

    public static Order toCreationEntity(OrderCreationDTO dto){
        return Order.builder()
                .totalPrice(dto.getTotalPrice())
                .orderNumber(dto.getOrderNumber())
                .products(dto.getProducts())
                .build();
    }

    public static OrderCreationDTO toCreationDto(Order order){
        return OrderCreationDTO.builder()
                .totalPrice(order.getTotalPrice())
                .orderNumber(order.getOrderNumber())
                .products(order.getProducts())
                .build();
    }
}
