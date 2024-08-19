package com.example.ProiectMagazinSuplimenteAlimentare.controller;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.ProductCategoryCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.TagCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.TagDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.exceptions.ApiExceptionResponse;
import com.example.ProiectMagazinSuplimenteAlimentare.service.tag.ProductTagService;
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
 *  ProductTagController class is used to transfer the product tag data from the server to the client.
 */
@RestController
@RequestMapping("/product-tags")
@Tag(name = "ProductTag", description = "ProductTag Api")
@CrossOrigin
@Validated
public class ProductTagController {
    private final ProductTagService productTagService;

    public ProductTagController(ProductTagService productTagService) {
        this.productTagService = productTagService;
    }

    @Operation(
            summary = "Fetch all tags",
            description = "fetches all tags entities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping
    public ResponseEntity findAllProductTag() {
        return ResponseEntity.status(HttpStatus.OK).body(productTagService.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity findProductTagByName(@Parameter(required = true, description = "The name of the product tag") @PathVariable String name) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(productTagService.findProductTagByName(name));
    }

    @PostMapping("/create")
    public ResponseEntity createProductTag(@Valid @RequestBody TagCreationDTO tag) {
        return ResponseEntity.status(HttpStatus.OK).body(productTagService.saveProductTag(tag));
    }

    @PutMapping
    public ResponseEntity updateProductTag(@Valid @RequestBody TagDTO tag) {
        return ResponseEntity.status(HttpStatus.OK).body(productTagService.updateProductTag(tag));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productTagService.deleteProductTag(id));
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
