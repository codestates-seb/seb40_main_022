package com.backend.fitchallenge.domain.postcomment.controller;

import com.backend.fitchallenge.domain.post.service.PostService;
import com.backend.fitchallenge.domain.postcomment.dto.CommentCreate;
import com.backend.fitchallenge.domain.postcomment.dto.CommentGet;
import com.backend.fitchallenge.domain.postcomment.dto.CommentUpdate;
import com.backend.fitchallenge.domain.postcomment.service.CommentService;
import com.backend.fitchallenge.global.annotation.AuthMember;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
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
@RequestMapping("/dailyPosts/{id}")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    private ResponseEntity<?> create(@PathVariable("id") Long id,
                                  @AuthMember MemberDetails memberDetails,
                                  @Valid @RequestBody CommentCreate commentCreate) {
        log.info("content {}", commentCreate.getContent());
        Long result = commentService.createComment(id, memberDetails.getMemberId(), commentCreate);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * 댓글 조회 - 무한 스크롤 페이지네이션
     * @param pageable default size = 10, page = 0
     * @return 댓글 목록 최신순으로 리턴
     */
    @GetMapping("/comments")
    private ResponseEntity<?> getList(@PathVariable("id") Long id,
           @ModelAttribute CommentGet commentGet,
           @PageableDefault(size = 5)  Pageable pageable) {

        return new ResponseEntity<>(commentService.getCommentList(id, commentGet.getLastCommentId(),  pageable), HttpStatus.OK);
    }

    @PatchMapping("/comments/{comments-id}")
    private ResponseEntity<?> update(@PathVariable("id") Long id,
                                     @PathVariable("comments-id") Long commentId,
                                 @AuthMember MemberDetails memberDetails,
                                 @Valid @RequestBody   CommentUpdate commentUpdate) {

        return new ResponseEntity<>(commentService.updateComment(commentId, memberDetails.getMemberId(), commentUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/comments/{comments-id}")
    private ResponseEntity<?> delete(@PathVariable("id") Long id,
                                     @PathVariable("comments-id") Long commentId,
                                     @AuthMember MemberDetails memberDetails
                                    ) {

        commentService.deleteComment(commentId,memberDetails.getMemberId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
