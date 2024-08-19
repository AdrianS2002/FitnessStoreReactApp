package com.example.ProiectMagazinSuplimenteAlimentare.service.comment;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.CommentCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.CommentDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.CommentToProductAtribution;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.CommentToUserAtributtionDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Comment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * CommentService interface is used to create the methods that will be implemented in the CommentServiceImpl class.
 */
@Component
public interface CommentService {

    List<Comment> findAll();
    Comment findCommentById(Long id);
    CommentDTO saveComment(CommentCreationDTO comment);
    CommentDTO updateComment(CommentDTO comment);
    List<Comment> findCommentsByProductId(Long productId);
    void deleteAllByProductId(Long productId);

    Comment assignProduct(CommentToProductAtribution dto);

    Comment assignUser(CommentToUserAtributtionDTO dto);

    boolean deleteComment(Long id);
}
