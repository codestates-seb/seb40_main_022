package com.backend.fitchallenge.domain.question.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepositoryCustom {

    Long pagingCount();
}
