package com.backend.fitchallenge.domain.postcomment.service;

import com.backend.fitchallenge.domain.member.Member;
import com.backend.fitchallenge.domain.post.entity.Post;
import com.backend.fitchallenge.domain.post.service.PostService;
import com.backend.fitchallenge.domain.postcomment.dto.CommentCreate;
import com.backend.fitchallenge.domain.postcomment.dto.CommentResponse;
import com.backend.fitchallenge.domain.postcomment.dto.CommentUpdate;
import com.backend.fitchallenge.domain.postcomment.dto.CommentUpdateResponse;
import com.backend.fitchallenge.domain.postcomment.entity.PostComment;
import com.backend.fitchallenge.domain.postcomment.exception.CommentNotFound;
import com.backend.fitchallenge.domain.postcomment.repository.CommentRepository;
import com.backend.fitchallenge.domain.postcomment.repository.QueryCommentRepository;
import com.backend.fitchallenge.global.dto.response.MultiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final PostService postService;
    private final CommentRepository commentRepository;
    private final QueryCommentRepository queryCommentRepository;

    public Long createComment(Long postId, CommentCreate commentCreate) {
        Post post = postService.findPostById(postId);
        Member member = post.getMember();

        PostComment postComment = commentCreate.toEntity(post, member);

        return commentRepository.save(postComment).getId();
    }

    public MultiResponse<?> getCommentList(Long postId, Pageable pageable) {

        Long total = queryCommentRepository.pagingCount();

        PageImpl<CommentResponse> commentResponses = new PageImpl<>(queryCommentRepository.findPostComments(postId, pageable).stream()
                .map(postComment -> CommentResponse.toResponse(postComment, postComment.getMember()))
                .collect(Collectors.toList()), pageable, total);
        return MultiResponse.of(commentResponses);
    }

    public CommentUpdateResponse updateComment(Long commentId, CommentUpdate commentUpdate) {
        PostComment postComment = findCommentById(commentId);
        postComment.patch(commentUpdate);
       return CommentUpdateResponse.toResponse(postComment);
    }

    public void deleteComment(Long commentId) {
        commentRepository.delete(findCommentById(commentId));
    }

    private PostComment findCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(CommentNotFound::new);
    }


}
