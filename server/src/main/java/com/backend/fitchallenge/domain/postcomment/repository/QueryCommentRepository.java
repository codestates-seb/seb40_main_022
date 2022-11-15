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
     * 게시물 댓글 조회 - 페이지네이션
     * size 10개 default 버튼 누를때마다 다음페이지
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

    private BooleanExpression ltCommentId(Long commentId) {
        return isEmpty(commentId) ? null : postComment.id.lt(commentId);
    }

    private BooleanExpression postIdEq(Long postId) {
        return isEmpty(postId) ? null : postComment.post.id.eq(postId);
    }


}
