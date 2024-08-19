package com.example.ProiectMagazinSuplimenteAlimentare.controller;


import com.example.ProiectMagazinSuplimenteAlimentare.dto.ProductCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.ProductDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.exceptions.ApiExceptionResponse;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Comment;
import com.example.ProiectMagazinSuplimenteAlimentare.service.comment.CommentService;
import com.example.ProiectMagazinSuplimenteAlimentare.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.xml.bind.JAXBException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  ProductController class is used to transfer the product data from the server to the client.
 */

@RestController
@CrossOrigin
@Tag(name = "Product", description = "Product Api")
@RequestMapping("/product")
@Validated
public class ProductController {

    private final ProductService productService;

    private final CommentService commentService;

    public ProductController(ProductService productService, CommentService commentService) {
        this.productService = productService;
        this.commentService = commentService;
    }

    @Operation(
            summary = "Fetch all products",
            description = "fetches all products entities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping
    public ResponseEntity findAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity findProductByName(@Parameter(required = true, description = "The name of the product") @PathVariable String name) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findProductByName(name));
    }
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportProductsToXML() {
        try {
            String xmlData = productService.exportProductsToXML();
            byte[] bytes = xmlData.getBytes(StandardCharsets.UTF_8);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename("products.xml")
                    .build());

            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } catch (JAXBException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping

    public ResponseEntity saveProduct(@Valid @RequestBody ProductCreationDTO productDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.saveProduct(productDTO));
    }

    @PutMapping
    public ResponseEntity updateProduct(@Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body( productService.deleteProduct(id));
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

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getProductComments(@PathVariable Long id) {
        List<Comment> comments = commentService.findCommentsByProductId(id);
        return ResponseEntity.ok(comments);
    }

}