package com.example.boardservice.web.dto.like_dto;

import com.example.boardservice.domain.Like;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentLikeResponseDto {

    private final Long memberId;
    private final Long commentId;

    @Builder
    public CommentLikeResponseDto(Like like) {
        this.memberId = like.getMembersId();
        this.commentId = like.getCommentsId();
    }
}
