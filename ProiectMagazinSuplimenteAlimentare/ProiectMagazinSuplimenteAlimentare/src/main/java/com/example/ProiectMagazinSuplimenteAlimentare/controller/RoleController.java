package com.example.ProiectMagazinSuplimenteAlimentare.controller;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.RoleCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.RoleDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.exceptions.ApiExceptionResponse;
import com.example.ProiectMagazinSuplimenteAlimentare.service.role.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 *  RoleController class is used to transfer the role data from the server to the client.
 */
@RestController
@CrossOrigin
@Tag(name = "Role", description = "Role Api")
@RequestMapping("/role")
@Validated
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Operation(
            summary = "Fetch all products",
            description = "fetches all products entities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping
    public ResponseEntity findAllRoles() {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.findAll());
    }


    @GetMapping("/{name}")
    public ResponseEntity findRoleByName(@Parameter(required = true, description = "The name of the role") @PathVariable String name) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.findRoleByRole(name));
    }

    @PostMapping
    public ResponseEntity saveRole(@Valid @RequestBody RoleCreationDTO roleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.saveRole(roleDTO));
    }

    @PutMapping
    public ResponseEntity updateRole(@Valid @RequestBody RoleDTO role) {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.updateRole(role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRole(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.deleteRole(id));
    }
}
