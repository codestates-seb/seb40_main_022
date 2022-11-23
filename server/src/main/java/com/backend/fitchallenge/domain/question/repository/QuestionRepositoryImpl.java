package com.backend.fitchallenge.domain.question.repository;

import com.backend.fitchallenge.domain.question.dto.request.QuestionSearch;
import com.backend.fitchallenge.domain.question.entity.Question;
import com.backend.fitchallenge.global.dto.request.PageRequest;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Question> findQuestionWithWriter(Long id) {
        return Optional.ofNullable(
                jpaQueryFactory
                .select(question)
                .from(question)
                .leftJoin(question.member).fetchJoin()
                .where(question.id.eq(id))
                .fetchOne());
    }

    @Override
    public List<Tuple> findList(PageRequest pageable) {
        return jpaQueryFactory
                .select(question, question.answers.size())
                .from(question)
                .leftJoin(question.member).fetchJoin()
                .limit(pageable.getSize())
                .offset(pageable.getOffset())
                .orderBy(getOrderSpecifier(pageable))
                .fetch();
    }

    @Override
    public List<Tuple> findList(PageRequest pageable, QuestionSearch questionSearch) {
        return jpaQueryFactory
                .select(question, question.answers.size())
                .from(question)
                .where(question.title.contains(questionSearch.getQuery())
                        .or(question.content.contains(questionSearch.getQuery())))
                .where(question.questionTag.stringValue().eq(questionSearch.getTag().get(0)))
                .limit(pageable.getSize())
                .offset(pageable.getOffset())
                .orderBy(getOrderSpecifier(pageable))
                .fetch();
    }

    private OrderSpecifier<?> getOrderSpecifier(PageRequest pageable) {

        for (Sort.Order order : pageable.getDynamicSort()) {
            Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

            switch (order.getProperty()) {
                case "recent" :
                    return new OrderSpecifier<>(direction, question.id);
                case "hot" :
                    return new OrderSpecifier<>(direction, question.view);
            }
        }
        return null;
    }
}
