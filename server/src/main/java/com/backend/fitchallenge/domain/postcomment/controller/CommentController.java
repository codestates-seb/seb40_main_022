package com.backend.fitchallenge.domain.postcomment.controller;

import com.backend.fitchallenge.domain.post.service.PostService;
import com.backend.fitchallenge.domain.postcomment.dto.CommentCreate;
import com.backend.fitchallenge.domain.postcomment.dto.CommentUpdate;
import com.backend.fitchallenge.domain.postcomment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/dailyposts/{id}")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    private ResponseEntity<?> create(@PathVariable("id") Long id,
                                  @Valid @RequestBody CommentCreate commentCreate) {
        log.info("content {}", commentCreate.getContent());
        Long result = commentService.createComment(id, commentCreate);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/comments")
    private ResponseEntity<?> getList(@PathVariable("id") Long id,
           @RequestParam Long lastCommentId,
           @PageableDefault(size = 10)  Pageable pageable) {

        return new ResponseEntity<>(commentService.getCommentList(id,lastCommentId, pageable), HttpStatus.OK);
    }

    @PatchMapping("/comments/{comments-id}")
    private ResponseEntity<?> update(@PathVariable("id") Long id,
                                     @PathVariable("comments-id") Long commentId,
                                 @Valid @RequestBody   CommentUpdate commentUpdate) {

        return new ResponseEntity<>(commentService.updateComment(commentId, commentUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/comments/{comments-id}")
    private ResponseEntity<?> delete(@PathVariable("id") Long id,
                                     @PathVariable("comments-id") Long commentId) {

        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
