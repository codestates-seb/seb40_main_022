package com.backend.fitchallenge.domain.question.repository;

import com.backend.fitchallenge.domain.question.dto.request.QuestionSearch;
import com.backend.fitchallenge.domain.question.entity.Question;
import com.backend.fitchallenge.domain.question.dto.request.PageRequest;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

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

        log.info("repository findList query: {}", questionSearch.getQuery());
        log.info("repository findList tag: {}", questionSearch.getTag().toString());
        return jpaQueryFactory
                .select(question, question.answers.size())
                .from(question)
                .where(question.title.contains(questionSearch.getQuery())
                        .or(question.content.contains(questionSearch.getQuery())))
                //이 경우, QuestionTag.stringValue()는 'WORK, DIET, POSTURE' 등을 의미합니다.
                //eq의 매개변수로 (1) enum의 value(ex_"운동", "식단", "자세", ...) 혹은 (2) enum 객체를 받지 않는 이유는
                // (1)은 queryDsl 조건식에서 추출할 수 없기 때문이고
                // (2)는 questionTag를 검색조건에 넣지 않았을 때를 처리하기가 까다롭기 때문입니다.
                .where(questionTagEq(questionSearch.getTag()))
                .limit(pageable.getSize())
                .offset(pageable.getOffset())
                .orderBy(getOrderSpecifier(pageable))
                .fetch();
    }

    private BooleanExpression questionTagEq(String tagStringValue) {
        //QuestionSearchQuery의 queryParsing()에서 questionTag에 해당하는 값이 입력되지 않으면 ""을 넘기도록 설정했습니다.
        return tagStringValue.equals("") ? null : question.questionTag.stringValue().eq(tagStringValue);
    }

    private OrderSpecifier<?> getOrderSpecifier(PageRequest pageable) {
        log.info("[getOrderSpecifier] dynamicSort: {}", pageable.getDynamicSort());
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
