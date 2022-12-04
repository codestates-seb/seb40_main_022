package com.backend.fitchallenge.domain.answer.repository;

import com.backend.fitchallenge.domain.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query("select a.member.id from Answer a where a.id = :answerId")
    Long findMemberIdByAnswerId(@Param("answerId") Long answerId);
}
