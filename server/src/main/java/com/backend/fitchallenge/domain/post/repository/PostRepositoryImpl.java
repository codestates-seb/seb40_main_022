package com.backend.fitchallenge.domain.post.repository;

import com.backend.fitchallenge.domain.like.entity.QLikes;
import com.backend.fitchallenge.domain.tag.domain.QTag;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.backend.fitchallenge.domain.like.entity.QLikes.*;
import static com.backend.fitchallenge.domain.member.entity.QMember.member;
import static com.backend.fitchallenge.domain.post.entity.QPost.post;
import static com.backend.fitchallenge.domain.post.entity.QPostTag.postTag;
import static com.backend.fitchallenge.domain.tag.domain.QTag.*;
import static org.springframework.util.ObjectUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements  PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    /**
     * post + 댓글수 조회
     * member(작성자) fetchJoin
     * @return 마지막 postId보다 작은 Id의 게시물들을 최신순으로 반환
     * leftJoin like, likeId 가져와서 ifPresent면  true 아니면 false
     */
    public List<Tuple> findList(Long lastPostId,Long memberId, Pageable pageable) {

       return  jpaQueryFactory
                .select(post, likes.id,post.postComments.size())
                .from(post)
                .leftJoin(post.member, member).fetchJoin()
                .leftJoin(post.likes, likes)
                 .on(likes.member.id.eq(memberId))
                .where(ltPostId(lastPostId)) //no -offset 페이징 처리
                .limit(pageable.getPageSize()+1) //마지막 페이지인지 여부 확인하기위해 +1 해서 조회
                .orderBy(post.id.desc()) // 최신순
                .fetch();

    }


    @Override
    public List<Tuple> findListWithoutLogin(Long lastPostId, Pageable pageable) {
        return  jpaQueryFactory
                .select(post,post.postComments.size())
                .from(post)
                .leftJoin(post.member, member).fetchJoin()
                .where(ltPostId(lastPostId)) //no -offset 페이징 처리
                .limit(pageable.getPageSize()+1) //마지막 페이지인지 여부 확인하기위해 +1 해서 조회
                .orderBy(post.id.desc()) // 최신순
                .fetch();
    }


    public List<Tuple> findSearchList( Long memberId,List<Long> postIds) {

        return jpaQueryFactory
                .selectDistinct(post, likes.id,post.postComments.size())
                .from(post)
                .leftJoin(post.member, member).fetchJoin()
                .leftJoin(post.likes, likes)
                .on(likes.member.id.eq(memberId))
                .where(inPostIds(postIds))
                .orderBy(post.id.desc())
                .fetch();
    }


    @Override
    public List<Tuple> findSearchListWithoutLogin(List<Long> postIds) {
        return jpaQueryFactory
                .selectDistinct(post, post.postComments.size())
                .from(post)
                .leftJoin(post.member, member).fetchJoin()
                .where(inPostIds(postIds))
                .orderBy(post.id.desc())
                .fetch();
    }

    // no -offset 방식 처리 메서드
    private BooleanExpression ltPostId(Long postId) {
        return isEmpty(postId) ? null : post.id.lt(postId);
    }

    private BooleanExpression inPostIds(List<Long> postIds) {
        return postIds.size() == 0 ? null : post.id.in(postIds);
    }


}
