package com.backend.fitchallenge.domain.question.repository;

import com.backend.fitchallenge.domain.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionRepositoryCustom {
    @Query("select q.member.id from Question q where q.id = :questionId")
    Long findMemberIdByQuestionId(Long questionId);
}
