package com.backend.fitchallenge.domain.answer.repository;

import com.backend.fitchallenge.domain.answer.entity.Answer;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.backend.fitchallenge.domain.answer.entity.QAnswer.answer;
import static com.backend.fitchallenge.domain.answercomment.entity.QAnswerComment.answerComment;

@Repository
@RequiredArgsConstructor
public class QueryAnswerRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<Answer> findAnswersAndCommentsWithWriters(Long questionId) {
        return jpaQueryFactory
                .select(answer)
                .from(answer)
                .leftJoin(answer.member).fetchJoin()
                .leftJoin(answer.comments, answerComment).fetchJoin()
                .leftJoin(answerComment.member).fetchJoin()
                .where(answer.question.id.eq(questionId))
                .fetch();
    }
}
