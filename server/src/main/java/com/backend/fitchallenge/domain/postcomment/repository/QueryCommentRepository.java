package com.backend.fitchallenge.domain.postcomment.repository;

import com.backend.fitchallenge.domain.postcomment.entity.PostComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import java.util.List;

import static com.backend.fitchallenge.domain.postcomment.entity.QPostComment.postComment;

@Repository
@RequiredArgsConstructor
public class QueryCommentRepository {

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 게시물 댓글 조회 - 페이지네이션
     * size 10개 default 버튼 누를때마다 다음페이지
     */

    public List<PostComment> findPostComments(Long postId, Pageable pageable) {
        return jpaQueryFactory.selectFrom(postComment)
                .leftJoin(postComment.member).fetchJoin()
                .where(postComment.post.id.eq(postId))
                .orderBy(postComment.id.desc())
                .fetch();




    }

    public Long pagingCount() {
        return jpaQueryFactory.select(postComment.id.count())
                .from(postComment)
                .fetchOne();
    }


}
