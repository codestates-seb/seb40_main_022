package com.backend.fitchallenge.domain.post.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.backend.fitchallenge.domain.member.QMember.member;
import static com.backend.fitchallenge.domain.post.entity.QPicture.picture;
import static com.backend.fitchallenge.domain.post.entity.QPost.post;
import static org.springframework.util.ObjectUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements  PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * post + 작성자 정보 + 댓글, 좋아요, 태그
     *
     * @param pageable 기본 사이즈 3, 무한 페이지 스크롤 슬라이스로
     * @return
     */
    public List<Tuple> findListWithTag(Long lastPostId, Pageable pageable) {

       return  jpaQueryFactory
                .select(post, post.postComments.size())
                .from(post)
                .leftJoin(post.member, member).fetchJoin()
                .where(ltPostId(lastPostId)) //no -offset 페이징 처리
                .limit(pageable.getPageSize()+1) //마지막 페이지인지 여부 확인하기위해 +1 해서 조회
                .orderBy(post.id.desc()) // 최신순
                .fetch();

    }

    // no -offset 방식 처리 메서드
    private BooleanExpression ltPostId(Long postId) {
        return isEmpty(postId) ? null : post.id.lt(postId);
    }


    @Override
    public Long pagingCount() {
        return jpaQueryFactory
                .select(post.id.count())
                .from(post)
                .fetchOne();
    }


}
