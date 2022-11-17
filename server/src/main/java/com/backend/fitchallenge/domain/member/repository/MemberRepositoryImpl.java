package com.backend.fitchallenge.domain.member.repository;

import com.backend.fitchallenge.domain.challenge.dto.QRankingDto;
import com.backend.fitchallenge.domain.challenge.dto.RankingCondition;
import com.backend.fitchallenge.domain.challenge.dto.RankingDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.backend.fitchallenge.domain.member.entity.QMember.member;
import static com.backend.fitchallenge.domain.member.entity.QMemberActivity.*;
import static org.springframework.util.ObjectUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public List<RankingDto> rankingList(RankingCondition condition) {
       return jpaQueryFactory
                .select(new QRankingDto(
                        member.id,
                        member.username,
                        member.profileImage,
                        member.height,
                        member.weight,
                        memberActivity.point,
                        memberActivity.dayCount
                ))
                .from(member)
                .where(splitEq(condition.getSplit()),
                        heightGoe(condition.getHeightGoe()),
                        heightLt(condition.getHeightLt()),
                        weightGoe(condition.getWeightGoe()),
                        weightLt(condition.getWeightLt()),
                        periodGoe(condition.getPeriodGoe()),
                        periodLt(condition.getPeriodLt()))
                .fetch();
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
