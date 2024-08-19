package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
/**
 *  ProductDTO class is used to transfer the product data from the server to the client.
 */
@Getter
@Setter
@Builder
public class ProductDTO {

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
