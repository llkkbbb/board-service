package com.example.boardservice.module.post.web;

import com.example.boardservice.module.post.service.PostService;
import com.example.boardservice.module.post.web.dto.request.RequestPostSaveDto;
import com.example.boardservice.module.post.web.dto.request.RequestPostUpdateDto;
import com.example.boardservice.module.post.web.dto.request.RequestSearchPostDto;
import com.example.boardservice.module.post.web.dto.response.ResponsePostPagingDto;
import com.example.boardservice.module.post.web.dto.response.ResponsePostSaveDto;
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
    public ResponseEntity<ResponsePostSaveDto> createPost(@Valid @RequestBody RequestPostSaveDto requestDto) {
        ResponsePostSaveDto responseDto = postService.savePost(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<ResponsePostSaveDto> getPosts(@PathVariable("id") Long postId) {
        ResponsePostSaveDto responseDto = postService.findByPostId(postId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/posts")
    public ResponseEntity<ResponsePostPagingDto> getPostList(
            @RequestBody(required = false) RequestSearchPostDto requestSearchPostDto,
            @PageableDefault(size = 5) Pageable pageable) {
        ResponsePostPagingDto responsePagingDto = postService.findPosts(requestSearchPostDto, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(responsePagingDto);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable("id") Long postId, @RequestBody RequestPostUpdateDto requestDto) {
        postService.updateAfterFindPost(postId, requestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long postId) {
        postService.removePost(postId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
