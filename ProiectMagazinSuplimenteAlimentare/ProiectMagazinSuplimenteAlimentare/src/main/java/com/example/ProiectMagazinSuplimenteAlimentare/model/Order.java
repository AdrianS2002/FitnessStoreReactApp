package com.example.ProiectMagazinSuplimenteAlimentare.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Order is the class that creates the order object

 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
@ToString
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double totalPrice;
    private Long orderNumber;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private List<Product> products = new ArrayList<>();

}
