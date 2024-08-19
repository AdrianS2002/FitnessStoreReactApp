package com.example.ProiectMagazinSuplimenteAlimentare.controller;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.CommentCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.CommentDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.OrderCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.OrderDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Comment;
import com.example.ProiectMagazinSuplimenteAlimentare.service.comment.CommentService;
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
 * CommentController class is the class that creates the comment controller object
 */
@RestController
@CrossOrigin
@Tag(name = "Order", description = "Order Api")
@RequestMapping("/comment")
@Validated
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @Operation(
            summary = "Fetch all comments",
            description = "fetches all comments entities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })

    @GetMapping
    public ResponseEntity findAllComments() {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findOrderByOrderNumber(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findCommentById(id));
    }
    @PostMapping("/create")
    public ResponseEntity createComment(@Valid @RequestBody CommentCreationDTO comment) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.saveComment(comment));
    }

    @PutMapping
    public ResponseEntity uupdateCommentpdateOrder(@Valid @RequestBody CommentDTO order) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComment(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.deleteComment(id));
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
