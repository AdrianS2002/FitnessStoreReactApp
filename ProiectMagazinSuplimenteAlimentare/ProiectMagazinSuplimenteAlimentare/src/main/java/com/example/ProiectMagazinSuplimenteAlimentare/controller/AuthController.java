package com.example.ProiectMagazinSuplimenteAlimentare.controller;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.AuthDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.LogoutDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.exceptions.ApiExceptionResponse;
import com.example.ProiectMagazinSuplimenteAlimentare.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
/**
 *  AuthController class is used to transfer the authentication data from the server to the client.
 */
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthDTO auth) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(auth));
    }
    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody LogoutDTO logoutDTO) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(userService.logout(logoutDTO));
    }

}