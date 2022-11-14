package com.backend.fitchallenge.domain.post.repository;

import com.backend.fitchallenge.domain.post.entity.Post;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


import static com.backend.fitchallenge.domain.member.QMember.member;
import static com.backend.fitchallenge.domain.picture.entity.QPicture.picture;
import static com.backend.fitchallenge.domain.post.entity.QPost.post;
import static com.backend.fitchallenge.domain.post.entity.QPostTag.postTag;
import static com.backend.fitchallenge.domain.tag.domain.QTag.tag;

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
    @Override
    public List<Post> findListWithTag(Pageable pageable) {

        return jpaQueryFactory
                .select(post)
                .from(post)
                .leftJoin(post.member, member).fetchJoin()
                .leftJoin(post.postTags, postTag).fetchJoin()
                .leftJoin(postTag.tag, tag).fetchJoin()
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(post.id.desc())
                .fetch();
    }

    @Override
    public Long pagingCount() {
        return jpaQueryFactory
                .select(post.id.count())
                .from(post)
                .fetchOne();
    }

    @Override
    public List<Tuple> findListWithPicture(Pageable pageable) {
        return jpaQueryFactory
                .select(post.id, picture.path)
                .from(post)
                .join(post.pictures, picture)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .where(post.id.eq(picture.post.id))
                .fetch();
    }
}
