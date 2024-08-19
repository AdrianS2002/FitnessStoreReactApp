package com.example.ProiectMagazinSuplimenteAlimentare.controller;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.PasswordUpdateDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.UserCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.UserDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.exceptions.ApiExceptionResponse;
import com.example.ProiectMagazinSuplimenteAlimentare.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 *  UserController class is used to transfer the user data from the server to the client.
 */
@RestController
@RequestMapping ("/user")
@Tag(name = "User", description = "Owner Api")
@CrossOrigin
@Validated
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Fetch all users",
            description = "fetches all users entities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })

    @GetMapping
    public ResponseEntity findAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity findUserByUsername(@Parameter(required = true, description = "The name of the user") @PathVariable String name) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByUsername(name));
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@Valid @RequestBody UserCreationDTO user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.saveUser(user));
    }

    @PutMapping("/update-user")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO user) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserRoles(user));
    }
    @PutMapping("/update-password")
    public ResponseEntity updatePassword(@Valid @RequestBody PasswordUpdateDTO passwordUpdateDTO) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updatePassword(passwordUpdateDTO));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@Valid @RequestBody UserCreationDTO userCreationDTO) {
        UserDTO newUser = userService.saveNewUser(userCreationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }


}