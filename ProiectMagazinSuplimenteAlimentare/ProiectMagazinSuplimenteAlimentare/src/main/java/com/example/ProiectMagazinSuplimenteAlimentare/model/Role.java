package com.example.ProiectMagazinSuplimenteAlimentare.model;

import com.example.ProiectMagazinSuplimenteAlimentare.constants.RoleType;
import com.example.ProiectMagazinSuplimenteAlimentare.validators.RoleSubset;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Role class is used to create a role object with the following attributes: id, role.


 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(unique = true)
    @RoleSubset(anyOf = {RoleType.ADMIN, RoleType.USER}, message = "Role must be either 'ADMIN' or 'USER'")
    private String role;

// ?   @ManyToMany(fetch = FetchType.EAGER)
//    private List<User> users;



}
