package com.example.ProiectMagazinSuplimenteAlimentare.dto;


import com.example.ProiectMagazinSuplimenteAlimentare.model.Order;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Role;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;
/**
 *  UserDTO class is used to transfer the user data from the server to the client.
 */
@Getter
@Setter
@Builder
public class UserDTO {
    private Long id;
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Name must contain only letters.")
    private String name;
    @NonNull
    @Email(message = "Username provided is not a valid email.")
    private String username;
    @NonNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password must contain at least 8 characters, one uppercase letter, one lowercase letter and one number.")
    private String password;
    @Pattern(regexp="(^$|[0-9]{10})", message = "Telephone number must contain 10 digits.")
    @NonNull
    private String telephone;
    private Boolean flag;
    private List<Role> roles;
    private List<Order> orders;

}
