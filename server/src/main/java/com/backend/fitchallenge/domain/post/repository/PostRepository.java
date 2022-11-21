package com.backend.fitchallenge.domain.post.repository;

import com.backend.fitchallenge.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository  extends JpaRepository<Post, Long>, PostRepositoryCustom {

    Page<Post> findByMemberId(Long memberId, Pageable pageable);
}
