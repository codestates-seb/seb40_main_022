package com.backend.fitchallenge.domain.post.repository;

import com.backend.fitchallenge.domain.post.entity.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements  PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
}
