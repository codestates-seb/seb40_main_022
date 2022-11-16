package com.backend.fitchallenge.domain.postcomment.controller;

import com.backend.fitchallenge.domain.post.service.PostService;
import com.backend.fitchallenge.domain.postcomment.dto.CommentCreate;
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
@RequestMapping("/dailyposts/{id}")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 생성
     * @param id postId
     * @param memberDetails 로그인 세션정보
     * @param commentCreate 댓글 생성 요청
     * @return commentId
     */
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
     * @param id postId
     * @param lastCommentId 현재 페이지의 마지막 postId
     * @param pageable default size = 10, page = 0
     * @return 댓글 목록 최신순으로 리턴
     */
    @GetMapping("/comments")
    private ResponseEntity<?> getList(@PathVariable("id") Long id,
           @RequestParam Long lastCommentId,
           @PageableDefault(size = 10)  Pageable pageable) {

        return new ResponseEntity<>(commentService.getCommentList(id,lastCommentId,  pageable), HttpStatus.OK);
    }

    /**
     * 댓글 수정
     * @param commentId 댓글 Id
     * @param memberDetails 로그인 세션정보
     * @param commentUpdate 댓글 수정 요청정보
     * @return 수정한 댓글 정보
     */
    @PatchMapping("/comments/{comments-id}")
    private ResponseEntity<?> update(@PathVariable("id") Long id,
                                     @PathVariable("comments-id") Long commentId,
                                 @AuthMember MemberDetails memberDetails,
                                 @Valid @RequestBody   CommentUpdate commentUpdate) {

        return new ResponseEntity<>(commentService.updateComment(commentId, memberDetails.getMemberId(), commentUpdate), HttpStatus.OK);
    }

    /**
     *댓글 삭제
     * @param memberDetails 로그인 세션정보
     * @param commentId 댓글 Id
     * @return 응답상태코드 200
     */
    @DeleteMapping("/comments/{comments-id}")
    private ResponseEntity<?> delete(@PathVariable("id") Long id,
                                     @PathVariable("comments-id") Long commentId,
                                     @AuthMember MemberDetails memberDetails
                                    ) {

        commentService.deleteComment(commentId,memberDetails.getMemberId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
