package com.backend.fitchallenge.domain.like.service;

import com.backend.fitchallenge.domain.like.entity.Likes;
import com.backend.fitchallenge.domain.like.repository.LikeRepository;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.service.MemberService;
import com.backend.fitchallenge.domain.post.entity.Post;
import com.backend.fitchallenge.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostService postService;
    private final MemberService memberService;


    public void up(Long postId, Long memberId) {
        Post post = postService.findPostById(postId);
        Member member = memberService.findVerifiedMemberById(memberId);

        Likes likes = Likes.toEntity(member, post);
        likeRepository.up(likes);

    }

    public void cancel(Long postId, Long memberId) {
        likeRepository.cancel(postId, memberId);
    }
}
