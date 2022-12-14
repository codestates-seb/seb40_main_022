package com.backend.fitchallenge.domain.question.repository.jparepository;

import com.backend.fitchallenge.domain.question.dto.request.PageRequest;
import com.backend.fitchallenge.domain.question.dto.request.QuestionSearch;
import com.backend.fitchallenge.domain.question.entity.Question;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public Long pagingCount(QuestionSearch questionSearch) {
        return jpaQueryFactory
                .select(question.count())
                .from(question)
                .where()
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
                .orderBy(question.id.desc())
                .fetch();
    }

//    @Override
//    public List<Tuple> findList(PageRequest pageable, QuestionSearch questionSearch) {
//
//        return jpaQueryFactory
//                .select(question, question.answers.size())
//                .from(question)
//                .where(question.title.contains(questionSearch.getQuery())
//                        .or(question.content.contains(questionSearch.getQuery())))
//                //??? ??????, QuestionTag.stringValue()??? 'WORK, DIET, POSTURE' ?????? ???????????????.
//                //eq??? ??????????????? (1) enum??? value(ex_"??????", "??????", "??????", ...) ?????? (2) enum ????????? ?????? ?????? ?????????
//                // (1)??? queryDsl ??????????????? ????????? ??? ?????? ????????????
//                // (2)??? questionTag??? ??????????????? ?????? ????????? ?????? ??????????????? ???????????? ???????????????.
//                .where(questionTagEq(questionSearch.getTag()))
//                .limit(pageable.getSize())
//                .offset(pageable.getOffset())
//                .orderBy(getOrderSpecifier(pageable))
//                .fetch();
//    }

//    private BooleanExpression questionTagEq(String tagStringValue) {
//        //QuestionSearchQuery??? queryParsing()?????? questionTag??? ???????????? ?????? ???????????? ????????? ""??? ???????????? ??????????????????.
//        return tagStringValue.equals("") ? null : question.questionTag.stringValue().eq(tagStringValue);
//    }

//    private OrderSpecifier<?> getOrderSpecifier(PageRequest pageable) {
//        log.info("[getOrderSpecifier] dynamicSort: {}", pageable.getDynamicSort());
//        for (Sort.Order order : pageable.getDynamicSort()) {
//            Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
//
//            switch (order.getProperty()) {
//                case "recent" :
//                    return new OrderSpecifier<>(direction, question.id);
//                case "hot" :
//                    return new OrderSpecifier<>(direction, question.view);
//            }
//        }
//        return null;
//    }
}
