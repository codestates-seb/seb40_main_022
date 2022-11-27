package com.backend.fitchallenge.domain.like.controller;

import com.backend.fitchallenge.domain.like.service.LikeService;
import com.backend.fitchallenge.global.annotation.AuthMember;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("post/{id}/like")
public class LikeController {
    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<?> up(
            @PathVariable("id") Long postId,
            @AuthMember MemberDetails memberDetails
            ) {
        likeService.up(postId, memberDetails.getMemberId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/undo")
    public ResponseEntity<?> cancel(
            @PathVariable("id") Long postId,
            @AuthMember MemberDetails memberDetails
    ) {
        likeService.cancel(postId, memberDetails.getMemberId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
