package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
/**
 *  SuccessfulLoginDTO class is used to transfer the successful login data from the server to the client.
 */
@Getter
@Setter
@Builder
public class SuccessfulLoginDTO {
    private Long id;
    private String username;
    private RoleDTO[] roles;
}
