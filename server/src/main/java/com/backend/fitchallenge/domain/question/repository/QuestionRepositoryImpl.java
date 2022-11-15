package com.backend.fitchallenge.domain.question.repository;

import com.backend.fitchallenge.domain.question.entity.QQuestion;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.backend.fitchallenge.domain.question.entity.QQuestion.question;

@Repository
@RequiredArgsConstructor
@Slf4j
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Long pagingCount() {
        return jpaQueryFactory
                .select(question.count())
                .from(question)
                .fetchOne();
    }
}
