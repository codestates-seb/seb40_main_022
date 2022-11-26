package com.backend.fitchallenge.domain.challenge.repository;

import com.backend.fitchallenge.domain.challenge.entity.Challenge;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.backend.fitchallenge.domain.challenge.entity.QChallenge.*;

@Repository
@RequiredArgsConstructor
public class ChallengeRepositoryImpl implements ChallengeRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;


    /**
     * 챌린지 승락후 챌린저들의 이전에 제안하거나 제안받은 챌린지들 삭제
     * batch로 시작하는 시간에 삭제할지 논의 필요
     * @param memberIds
     */
    @Override
    public void deleteSuggest(List<Long> memberIds) {
        jpaQueryFactory.delete(challenge)
                .where(challenge.applicantId.in(memberIds)
                        .or(challenge.counterpartId.in(memberIds))
                        .and(challenge.challengeStatus.eq(Challenge.ChallengeStatus.SUGGESTED)))
                .execute();
    }

    public List<Challenge> ongoingChallenges(){
        return jpaQueryFactory.select(challenge)
                .from(challenge)
                .where(challenge.challengeStatus.eq(Challenge.ChallengeStatus.ONGOING))
                .fetch();
    }
}
