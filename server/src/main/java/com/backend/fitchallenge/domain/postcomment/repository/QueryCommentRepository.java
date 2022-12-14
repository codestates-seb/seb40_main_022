package com.backend.fitchallenge.domain.postcomment.repository;

import com.backend.fitchallenge.domain.postcomment.entity.PostComment;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import java.util.List;

import static com.backend.fitchallenge.domain.post.entity.QPost.post;
import static com.backend.fitchallenge.domain.postcomment.entity.QPostComment.postComment;
import static org.springframework.util.ObjectUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class QueryCommentRepository {

    private final JPAQueryFactory jpaQueryFactory;


    /**
     * 게시물 댓글 조회 - 무한 스크롤
     * member fetchJoin
     * 요청한 페이지의 마지막 댓글Id보다 작은 Id의 댓글조회
     * @param postId 와 댓글의 postId가 같은 댓글만 조회
     */
    public List<PostComment> findPostComments(Long postId, Long lastCommentId, Pageable pageable) {
        return jpaQueryFactory.selectFrom(postComment)
                .leftJoin(postComment.member).fetchJoin()
                .where(postIdEq(postId),
                        ltCommentId(lastCommentId))
                .orderBy(postComment.id.desc())
                .limit(pageable.getPageSize() +1)
                .fetch();
    }

    // 마지막 댓글 Id보다 작은 Id
    private BooleanExpression ltCommentId(Long commentId) {
        return isEmpty(commentId) ? null : postComment.id.lt(commentId);
    }

    // 댓글의 postId가  파라미터의 postId와 같은지
    private BooleanExpression postIdEq(Long postId) {
        return isEmpty(postId) ? null : postComment.post.id.eq(postId);
    }


}
