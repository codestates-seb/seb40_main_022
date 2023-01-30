package com.backend.fitchallenge.domain.question.repository;


import com.backend.fitchallenge.domain.question.dto.request.PageRequest;
import com.backend.fitchallenge.domain.question.dto.request.QuestionSearch;
import com.backend.fitchallenge.domain.question.entity.Question;

import com.querydsl.core.Tuple;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepositoryCustom {

    Long pagingCount();

    Optional<Question> findQuestionWithWriter(Long id);

    List<Tuple> findList(PageRequest pageable);

    List<Tuple> findList(PageRequest pageable, QuestionSearch questionSearch);
}
