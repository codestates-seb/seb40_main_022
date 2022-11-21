package com.backend.fitchallenge.domain.tag.repository;

import com.backend.fitchallenge.domain.post.entity.Post;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import java.util.List;

import static com.backend.fitchallenge.domain.post.entity.QPost.post;
import static com.backend.fitchallenge.domain.post.entity.QPostTag.postTag;
import static com.backend.fitchallenge.domain.tag.domain.QTag.tag;
import static org.springframework.util.ObjectUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class QueryTagRepository {

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 태그 목록 조회
     */
    public List<Long> findTagsByContent(List<String> tagNames) {
        return jpaQueryFactory.select(tag.id)
                .from(tag)
                .where(tag.content.in(tagNames))
                .fetch();
    }

    public List<Long> findPostByTag( Long lasPostId, List<Long> tagIds, Pageable pageable) {

        //distinct 필요할수도
        return jpaQueryFactory.selectDistinct(post.id)
                .from(postTag)
                .join(post)
                .on(postTag.post.id.eq(post.id))
               .where(
                      inTagIds(tagIds),
                      ltPostId(lasPostId))
                .limit(pageable.getPageSize()+1)
                .orderBy(post.id.desc())
                .fetch();
    }

    public BooleanExpression inTagIds(List<Long> tagIds) {
        return tagIds.size() == 0 ? null : postTag.tag.id.in(tagIds);
    }

    private BooleanExpression ltPostId(Long postId) {
        return isEmpty(postId) ? null : post.id.lt(postId);
    }

}
