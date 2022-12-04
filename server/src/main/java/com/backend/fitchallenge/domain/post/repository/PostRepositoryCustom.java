package com.backend.fitchallenge.domain.post.repository;

import com.backend.fitchallenge.domain.post.entity.Post;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepositoryCustom {

    List<Tuple> findList(Long lastPostId,Long memberId, Pageable pageable);

    List<Tuple> findListWithoutLogin(Long lastPostId, Pageable pageable);

    List<Tuple> findSearchList(Long memberId, List<Long> postIds);
    List<Tuple> findSearchListWithoutLogin( List<Long> postIds);

}
