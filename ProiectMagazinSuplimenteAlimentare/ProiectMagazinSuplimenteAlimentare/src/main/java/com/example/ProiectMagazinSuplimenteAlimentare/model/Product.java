package com.example.ProiectMagazinSuplimenteAlimentare.model;
import jakarta.persistence.*;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

/**
 * Product class is used to create a product object with the following attributes: id, name, description, price, quantity, image.
 */
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    @NotBlank(message = "Name is required")
    private String name;
    @Size(min = 3, max = 300, message = "Description must be between 3 and 100 characters")
    private String description;


    @DecimalMin(value = "0.4", message = "Price must be greater than 0.3")
    private Double price;

    @Min(value = 0, message = "Quantity cannot be less than 0")
    private Integer quantity;

    @Pattern(regexp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", message = "Image must be a valid URL")
    private String image;
}
