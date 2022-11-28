package com.backend.fitchallenge.domain.postcomment.service;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.exception.MemberNotExist;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.global.dto.response.SliceMultiResponse;
import com.backend.fitchallenge.domain.post.entity.Post;
import com.backend.fitchallenge.domain.post.service.PostService;
import com.backend.fitchallenge.domain.postcomment.dto.CommentCreate;
import com.backend.fitchallenge.domain.postcomment.dto.CommentResponse;
import com.backend.fitchallenge.domain.postcomment.dto.CommentUpdate;
import com.backend.fitchallenge.domain.postcomment.dto.CommentUpdateResponse;
import com.backend.fitchallenge.domain.postcomment.entity.PostComment;
import com.backend.fitchallenge.domain.postcomment.exception.CannotDeleteComment;
import com.backend.fitchallenge.domain.postcomment.exception.CannotUpdateComment;
import com.backend.fitchallenge.domain.postcomment.exception.CommentNotFound;
import com.backend.fitchallenge.domain.postcomment.repository.CommentRepository;
import com.backend.fitchallenge.domain.postcomment.repository.QueryCommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final PostService postService;
    private final CommentRepository commentRepository;
    private final QueryCommentRepository queryCommentRepository;
    private final MemberRepository memberRepository;

    public Long createComment(Long postId, Long memberId, CommentCreate commentCreate) {
        Post post = postService.findPostById(postId);
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotExist::new);

        PostComment postComment = commentCreate.toEntity(post, member);

        return commentRepository.save(postComment).getId();
    }

    /**
     * 댓글 목록 조회 - 무한 스크롤  NoOffset, Slice
     * 1. DB에서 member를 fetchJoin하여 postComment 조회
     * 2. 무한 스크롤 처리
     * @return 댓글목록 Slice
     */
    public SliceMultiResponse<?> getCommentList(Long postId, Long lastCommentId, Pageable pageable) {

        List<CommentResponse> commentResponses = queryCommentRepository.findPostComments(postId, lastCommentId, pageable).stream()
                .map(postComment -> CommentResponse.toResponse(postComment, postComment.getMember()))
                .collect(Collectors.toList());

        Slice<CommentResponse> result = checkLastPage(commentResponses, pageable);

        return SliceMultiResponse.of(result);
    }


    public CommentUpdateResponse updateComment(Long commentId, Long memberId, CommentUpdate commentUpdate) {
        PostComment postComment = findCommentById(commentId);

        if (postComment.getMember().getId() != memberId) {
            throw new CannotUpdateComment();
        }
        postComment.patch(commentUpdate);
       return CommentUpdateResponse.toResponse(postComment);
    }


    public void deleteComment(Long commentId, Long memberId) {

        PostComment postComment = findCommentById(commentId);

        if (postComment.getMember().getId() != memberId) {
            throw new CannotDeleteComment();
        }

        commentRepository.delete(postComment);
    }


    private PostComment findCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(CommentNotFound::new);
    }


    private Slice<CommentResponse> checkLastPage(List<CommentResponse> commentResponses, Pageable pageable ) {

        boolean hasNext = false;

        if (commentResponses.size() > pageable.getPageSize()) {
            hasNext = true;
            commentResponses.remove(pageable.getPageSize());
        }

        log.info("Slice PostResponse size = {}", commentResponses.size());

        return new SliceImpl<>(commentResponses, pageable, hasNext);
    }
}
