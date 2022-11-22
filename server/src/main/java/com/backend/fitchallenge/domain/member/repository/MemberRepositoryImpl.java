package com.backend.fitchallenge.domain.member.repository;

import com.backend.fitchallenge.domain.challenge.dto.request.QRankingDto;
import com.backend.fitchallenge.domain.challenge.dto.request.RankingCondition;
import com.backend.fitchallenge.domain.challenge.dto.request.RankingDto;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import org.springframework.data.domain.Pageable;
import java.util.List;

import static com.backend.fitchallenge.domain.member.entity.QMember.member;
import static com.backend.fitchallenge.domain.member.entity.QMemberActivity.*;
import static org.springframework.util.ObjectUtils.isEmpty;

@Repository
@RequiredArgsConstructor
//fixme: member테이블에 period 추가
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<RankingDto> rankingList(RankingCondition condition, Pageable pageable) {
       return jpaQueryFactory
                .select(new QRankingDto(
                        member.id,
                        member.username,
                        member.profileImage.path,
                        member.height,
                        member.weight,
                        memberActivity.point,
                        member.period,
                        member.challenge.id
                ))
                .from(member)
                .where(splitEq(condition.getSplit()),
                        heightGoe(condition.getHeightGoe()),
                        heightLt(condition.getHeightLt()),
                        weightGoe(condition.getWeightGoe()),
                        weightLt(condition.getWeightLt()),
                        periodGoe(condition.getPeriodGoe()),
                        periodLt(condition.getPeriodLt()))
               .offset(pageable.getOffset())
               .limit(pageable.getPageSize())
               .orderBy(memberActivity.point.desc())
               .fetch();
    }

    @Override
    public List<Member> findMemberList(List<Long> memberIds) {
        return jpaQueryFactory.selectFrom(member)
                .leftJoin(member.challenge).fetchJoin()
                .where(member.id.in(memberIds))
                .fetch();
    }

    @Override
    public Long pagingCount(RankingCondition condition, Pageable pageable) {
        return jpaQueryFactory
                .select(member.id.count())
                .from(member)
                .where(splitEq(condition.getSplit()),
                        heightGoe(condition.getHeightGoe()),
                        heightLt(condition.getHeightLt()),
                        weightGoe(condition.getWeightGoe()),
                        weightLt(condition.getWeightLt()),
                        periodGoe(condition.getPeriodGoe()),
                        periodLt(condition.getPeriodLt()))
                .fetchOne();
    }




    /* 검색조건 */

    // 분할 일치 
    private BooleanExpression splitEq(Integer split) {
        return isEmpty(split) ? null : member.split.eq(split);
    }
    // 키 이상
    private BooleanExpression heightGoe(Integer heightGoe) {
        return isEmpty(heightGoe) ? null : member.height.goe(heightGoe);
    }
    // 키 미만
    private BooleanExpression heightLt(Integer heightLt) {
        return isEmpty(heightLt) ? null : member.height.lt(heightLt);
    }
    // 몸무게 이상
    private BooleanExpression weightGoe(Integer weightGoe) {
        return isEmpty(weightGoe) ? null : member.weight.goe(weightGoe);
    }
    // 몸무게 미만
    private BooleanExpression weightLt(Integer weightLt) {
        return isEmpty(weightLt) ? null : member.weight.lt(weightLt);
    }  
    // 경력 이상
    private BooleanExpression periodGoe(Integer periodGoe) {
        return isEmpty(periodGoe) ? null : member.weight.goe(periodGoe);
    }
    // 경력 미만
    private BooleanExpression periodLt(Integer periodLt) {
        return isEmpty(periodLt) ? null : member.weight.lt(periodLt);
    }
    
    



}
