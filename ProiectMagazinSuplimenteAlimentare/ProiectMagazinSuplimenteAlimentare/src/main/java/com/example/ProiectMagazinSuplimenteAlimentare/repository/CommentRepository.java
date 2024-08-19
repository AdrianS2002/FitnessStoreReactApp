package com.example.ProiectMagazinSuplimenteAlimentare.repository;

import com.example.ProiectMagazinSuplimenteAlimentare.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CommentRepository interface is used to create the methods that will be implemented in the CommentRepository class.

 */
@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findCommentsByProductId(Long productId);
    void deleteAllByProductId(Long productId);
}
