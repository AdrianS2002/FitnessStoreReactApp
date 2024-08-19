package com.example.ProiectMagazinSuplimenteAlimentare.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * User class is used to create a user object with the following attributes: id, username, password, email, name, telephone.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Email(message = "Username provided is not a valid email.")
    private String username;

    @NonNull

    private String password;
//    @Email
//    private String email;
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Name must contain only letters.")
    private String name;

    @Pattern(regexp="(^$|[0-9]{10})")
    @NonNull
    private String telephone;

    private Boolean flag = false;



    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private  List<Order> orders ;//= new ArrayList<>();

}