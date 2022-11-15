package com.backend.fitchallenge.domain.postcomment.service;

import com.backend.fitchallenge.domain.member.Member;
import com.backend.fitchallenge.domain.post.dto.PostResponse;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
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

    public Long createComment(Long postId, CommentCreate commentCreate) {
        Post post = postService.findPostById(postId);
        Member member = post.getMember();

        PostComment postComment = commentCreate.toEntity(post, member);

        return commentRepository.save(postComment).getId();
    }

    public MultiResponse<?> getCommentList(Long postId, Long lastCommentId, Pageable pageable) {


        List<CommentResponse> commentResponses = queryCommentRepository.findPostComments(postId, lastCommentId, pageable).stream()
                .map(postComment -> CommentResponse.toResponse(postComment, postComment.getMember()))
                .collect(Collectors.toList());
        Slice<CommentResponse> result = checkLastPage(commentResponses, pageable);

        return MultiResponse.of(result);
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

    private Slice<CommentResponse> checkLastPage(List<CommentResponse> commentResponses, Pageable pageable ) {

        boolean hasNext = false;

        //조회 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (commentResponses.size() > pageable.getPageSize()) {
            hasNext = true;
            // 확인용으로 추가한데이터 remove
            commentResponses.remove(pageable.getPageSize());
        }

        log.info("Slice PostResponse size = {}", commentResponses.size());

        return new SliceImpl<>(commentResponses, pageable, hasNext);
    }
}
