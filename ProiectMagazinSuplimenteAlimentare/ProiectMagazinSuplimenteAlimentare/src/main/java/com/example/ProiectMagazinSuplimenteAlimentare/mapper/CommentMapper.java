package com.example.ProiectMagazinSuplimenteAlimentare.mapper;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.CommentCreationDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.dto.CommentDTO;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Comment;

import java.time.LocalDateTime;

public class CommentMapper {

    public static Comment toEntity(CommentDTO dto){
        return Comment.builder()
                .id(dto.getId())
                .text(dto.getText())
                .date(LocalDateTime.parse(dto.getDate()))
                .build();
    }

    public static CommentDTO toDto(Comment comment){
        return CommentDTO.builder()
                .id(comment.getId())
                .text(comment.getText())
                .date(comment.getDate().toString())
                .build();
    }

    public static Comment toCreationEntity(CommentCreationDTO dto){
        return Comment.builder()
                .text(dto.getText())
                .date(LocalDateTime.parse(dto.getDate().toString()))
                .build();
    }

    public static CommentCreationDTO toCreationDto(Comment comment){
        return CommentCreationDTO.builder()
                .text(comment.getText())
                .date(LocalDateTime.parse(comment.getDate().toString()))
                .build();
    }
}
