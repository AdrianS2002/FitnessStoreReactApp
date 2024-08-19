package com.example.ProiectMagazinSuplimenteAlimentare.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ProductTag class is used to create a product tag object with the following attributes: id, name, products.

 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 3, max = 50)
    @NotBlank
    private String name;

    @ManyToMany(cascade = CascadeType.DETACH,  fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    public String getDisplayName(){
        return "#"+name + " ";
    }

}

