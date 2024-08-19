package com.example.ProiectMagazinSuplimenteAlimentare.controller;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.ProductCategoryCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.ProductCategoryDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.UserCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.UserDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.exceptions.ApiExceptionResponse;
import com.example.ProiectMagazinSuplimenteAlimentare.service.productcategory.ProductCategoryService;
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
 *  ProductCategoryController class is used to transfer the product category data from the server to the client.
 */
@RestController
@RequestMapping("/product-category")
@Tag(name = "ProductCategory", description = "ProductCategory Api")
@CrossOrigin
@Validated
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @Operation(
            summary = "Fetch all categories",
            description = "fetches all users entities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping
    public ResponseEntity findAllProductCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(productCategoryService.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity findProductCategoryByName(@Parameter(required = true, description = "The name of the product category") @PathVariable String name) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(productCategoryService.findProductCategoryByName(name));
    }

    @PostMapping("/create")
    public ResponseEntity createProductCategory(@Valid @RequestBody ProductCategoryCreationDTO productCategory) {
        return ResponseEntity.status(HttpStatus.OK).body(productCategoryService.save(productCategory));
    }

    @PutMapping
    public ResponseEntity updateCategory(@Valid @RequestBody ProductCategoryDTO productCategory) {
        return ResponseEntity.status(HttpStatus.OK).body(productCategoryService.updateProductCategory(productCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productCategoryService.deleteProductCategory(id));
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
