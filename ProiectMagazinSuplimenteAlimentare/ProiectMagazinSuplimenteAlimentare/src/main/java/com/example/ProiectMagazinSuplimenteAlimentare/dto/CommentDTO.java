package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
/**
 * CommentDTO class is used to transfer the comment data from the server to the client.
 */
@Getter
@Setter
@Builder
public class CommentDTO {
    private Long id;
    @Size(max = 1000)
    private String text;
    private Long userId;
    private Long productId;
    private String date;
}
