package com.example.ProiectMagazinSuplimenteAlimentare.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *  CommentToProductAtribution class is used to transfer the comment to product atribution data from the server to the client.
 */
@Getter
@Setter
@NoArgsConstructor
public class CommentToProductAtribution {
    private Long commentId;
    private Long productId;
}
