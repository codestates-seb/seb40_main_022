package com.backend.fitchallenge.domain.postcomment.repository;

import com.backend.fitchallenge.domain.postcomment.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<PostComment, Long> {
}
