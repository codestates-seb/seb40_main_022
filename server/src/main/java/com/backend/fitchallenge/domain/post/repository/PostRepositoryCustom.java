package com.backend.fitchallenge.domain.post.repository;

import com.backend.fitchallenge.domain.post.entity.Post;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepositoryCustom {

    List<Post> findListWithTag(Pageable pageable);

    Long pagingCount();

    List<Tuple> findListWithPicture(Pageable pageable);
}
