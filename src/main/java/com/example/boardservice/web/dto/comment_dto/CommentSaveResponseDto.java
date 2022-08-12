package com.example.boardservice.web.dto.comment_dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CommentSaveResponseDto {

    private final String author;
    private final String content;
}