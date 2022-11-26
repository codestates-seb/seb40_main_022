package com.backend.fitchallenge.domain.member.repository;


import com.backend.fitchallenge.domain.challenge.dto.request.QRankingDto;
import com.backend.fitchallenge.domain.challenge.dto.request.RankingCondition;
import com.backend.fitchallenge.domain.challenge.dto.request.RankingDto;

import com.backend.fitchallenge.domain.challenge.entity.Challenge;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.post.entity.Post;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

import static com.backend.fitchallenge.domain.calendar.entity.QRecord.record;
import static com.backend.fitchallenge.domain.challenge.entity.QChallenge.challenge;
import static com.backend.fitchallenge.domain.member.entity.QMember.member;
import static com.backend.fitchallenge.domain.member.entity.QMemberActivity.*;
import static com.backend.fitchallenge.domain.post.entity.QPost.post;
import static org.springframework.util.ObjectUtils.isEmpty;

@Repository
@RequiredArgsConstructor
//fixme: member테이블에 period 추가
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Tuple> findMemberRecords(){
        return jpaQueryFactory.select(member, record) // <- join한거는 안 적어도됨.
                .from(member)
                .leftJoin(record).on(member.id.eq(record.member.id))
                .where(member.challenge.challengeStatus.eq(Challenge.ChallengeStatus.ONGOING))
                .fetch();
    }

    public List<Post> findList(Long lastPostId, Long memberId , Pageable pageable){

        return jpaQueryFactory
                .select(post)
                .from(member)
                .leftJoin(member.posts, post)
                 .on(post.member.id.eq(memberId))
                .where(ltPostId(lastPostId))
                .limit(pageable.getPageSize()+1)
                .orderBy(post.id.desc())
                .fetch();
    }

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

    public Member findOpponent(Long memberId) {
        //주석처리한 로직들은 '달력에 표시하기 위해 지난 챌린지에 대해서도 기록을 남겨놓는다면' 사용할 것들입니다.
//        LocalDate recordDate = LocalDate.of(record.getYear(), record.getMonth(), record.getDay());

        return jpaQueryFactory.selectFrom(member)
                .leftJoin(member.challenge, challenge).fetchJoin()
                .where(challengeMemberIdEq(memberId)
//                        , challengeStartLoe(recordDate)
//                        , challengeEndGoe(recordDate)
                )
                .fetchOne();
    }

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
        return isEmpty(periodGoe) ? null : member.period.goe(periodGoe);
    }
    // 경력 미만
    private BooleanExpression periodLt(Integer periodLt) {
        return isEmpty(periodLt) ? null : member.period.lt(periodLt);
    }


    private BooleanExpression ltPostId(Long postId) {
        return isEmpty(postId) ? null : post.id.lt(postId);
    }

    private BooleanExpression inPostIds(List<Long> postIds) {
        return postIds.size() == 0 ? null : post.id.in(postIds);
    }





    // 챌린지 상대와 일치
    private BooleanExpression challengeMemberIdEq(Long memberId) {
        return isEmpty(memberId) ? null : (
                member.id.eq(challenge.applicantId).and(challenge.counterpartId.eq(memberId))
                .or(member.id.eq(challenge.counterpartId).and(challenge.applicantId.eq(memberId))));
    }

    // 챌린지 시작 날짜 이후
    private BooleanExpression challengeStartLoe(LocalDate localDateLoe) {
        return isEmpty(localDateLoe) ? null : challenge.challengeStart.loe(localDateLoe);
    }

    // 챌린지 종료 날짜 이전전
   private BooleanExpression challengeEndGoe(LocalDate localDateGoe) {
        return isEmpty(localDateGoe) ? null : challenge.challengeEnd.goe(localDateGoe);
    }
}
