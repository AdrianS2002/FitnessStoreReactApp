package com.example.ProiectMagazinSuplimenteAlimentare.controller;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.OrderCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.OrderDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.service.order.OrderService;
import io.swagger.v3.oas.annotations.Operation;
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
 *  OrderController class is used to transfer the order data from the server to the client.
 */
@RestController
@CrossOrigin
@Tag(name = "Order", description = "Order Api")
@RequestMapping("/order")
@Validated
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @Operation(
            summary = "Fetch all products",
            description = "fetches all products entities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })

    @GetMapping
    public ResponseEntity findAllOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAll());
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity findOrderByOrderNumber(@PathVariable Long orderNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findFirstByOrderNumber(orderNumber));
    }

    @PostMapping("/create")
    public ResponseEntity createOrder(@Valid @RequestBody OrderCreationDTO order) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.saveOrder(order));
    }

    @PutMapping
    public ResponseEntity updateOrder(@Valid @RequestBody OrderDTO order) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrder(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.deleteOrder(id));
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
