package com.example.boardservice.module.post.web;

import com.example.boardservice.module.post.service.PostService;
import com.example.boardservice.module.post.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<PostSaveResponseDto> createPost(@Valid @RequestBody PostSaveRequestDto requestDto) {
        PostSaveResponseDto postSaveResponseDto = postService.savePost(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(postSaveResponseDto);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostSaveResponseDto> getPosts(@PathVariable("id") Long postId) {
        PostSaveResponseDto postSaveResponseDto = postService.findByPostId(postId);
        return ResponseEntity.status(HttpStatus.OK).body(postSaveResponseDto);
    }

    /**
     *  게시글 작성자, 제목, 게시글 내용으로 게시글 리스트 가져오기
     *
     *  1. 게시판 글을 최신순으로 조회하는 기능
     *  2. 작성자 이름으로 게시글을 조회하는 기능 -> 정렬 최신순
     *  3. 제목의 키워드 중심으로 조회하는 기능 -> 정렬 최신순
     *  4. 본문 내용 즉, 게시글의 내용의 중심으로 조회하는 기능 -> 정렬 최신순
     *
     */
    @GetMapping("/posts")
    public ResponseEntity<ResponsePostPagingDto> getPostList(
            @RequestBody(required = false) RequestSearchPostDto requestSearchPostDto,
            @PageableDefault(size = 5) Pageable pageable) {
        ResponsePostPagingDto responsePostPagingDto = postService.findPosts(requestSearchPostDto, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(responsePostPagingDto);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable("id") Long postId, @RequestBody PostUpdateRequestDto requestDto) {
        postService.updateAfterFindPost(postId, requestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long postId) {
        postService.removePost(postId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
