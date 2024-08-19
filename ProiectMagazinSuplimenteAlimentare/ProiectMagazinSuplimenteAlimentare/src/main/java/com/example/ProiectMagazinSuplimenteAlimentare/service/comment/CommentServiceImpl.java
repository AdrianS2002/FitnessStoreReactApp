package com.example.ProiectMagazinSuplimenteAlimentare.service.comment;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.CommentCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.CommentDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.CommentToProductAtribution;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.CommentToUserAtributtionDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.mapper.CommentMapper;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Comment;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Product;
import com.example.ProiectMagazinSuplimenteAlimentare.model.User;
import com.example.ProiectMagazinSuplimenteAlimentare.repository.CommentRepository;

import com.example.ProiectMagazinSuplimenteAlimentare.repository.ProductRepository;
import com.example.ProiectMagazinSuplimenteAlimentare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * CommentServiceImpl class implements the methods from the CommentService interface.
 *
 */
@Service
public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Comment> findAll() {
        return (List<Comment>) commentRepository.findAll();
    }

    @Override
    public Comment findCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow();
    }

    @Override
    public CommentDTO saveComment(CommentCreationDTO dto) {
        Comment comment = CommentMapper.toCreationEntity(dto);
        comment = commentRepository.save(comment);
        return CommentMapper.toDto(comment);
    }

    @Override
    public CommentDTO updateComment(CommentDTO dto) {
        Comment comment = CommentMapper.toEntity(dto);
       boolean exists = commentRepository.findById(comment.getId()).isPresent();
            if (exists) {
                comment = commentRepository.save(comment);
                return CommentMapper.toDto(comment);
            }
            return null;
    }

    @Override
    public Comment assignProduct(CommentToProductAtribution dto) {
        Comment comment = commentRepository.findById(dto.getCommentId()).orElseThrow();
        Product product = productRepository.findById(dto.getProductId()).orElseThrow() ;
        comment.setProduct(product);
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public List<Comment> findCommentsByProductId(Long productId) {
        return commentRepository.findCommentsByProductId(productId);
    }

    @Override
    public void deleteAllByProductId(Long productId) {
        commentRepository.deleteAllByProductId(productId);
    }

    @Override
    public Comment assignUser(CommentToUserAtributtionDTO dto) {
        Comment comment = commentRepository.findById(dto.getCommentId()).orElseThrow();
        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        comment.setUser(user);
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public boolean deleteComment(Long id) {
        commentRepository.deleteById(id);
        return commentRepository.findById(id).isEmpty();
    }
}
