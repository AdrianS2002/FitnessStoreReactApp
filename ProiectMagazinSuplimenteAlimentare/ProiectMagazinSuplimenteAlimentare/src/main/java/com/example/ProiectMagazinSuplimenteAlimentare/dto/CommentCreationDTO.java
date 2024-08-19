package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
/**
 * CommentCreationDTO class is used to transfer the comment creation data from the client to the server.
 */
@Getter
@Setter
@Builder
public class CommentCreationDTO {
    private String text;
    private Long productId;
    private Long userId;
    private LocalDateTime date;


}
