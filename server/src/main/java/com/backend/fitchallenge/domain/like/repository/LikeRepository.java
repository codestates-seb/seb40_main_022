package com.backend.fitchallenge.domain.like.repository;

import com.backend.fitchallenge.domain.like.entity.Likes;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.backend.fitchallenge.domain.like.entity.QLikes.*;

@Repository
@RequiredArgsConstructor
public class LikeRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    public void up(Likes likes) {
        em.persist(likes);
    }


    public Long cancel(Long postId, Long memberId) {

        return jpaQueryFactory.delete(likes)
                .where(likes.post.id.eq(postId)
                        .and(likes.member.id.eq(memberId))
                ).execute();
    }
}
