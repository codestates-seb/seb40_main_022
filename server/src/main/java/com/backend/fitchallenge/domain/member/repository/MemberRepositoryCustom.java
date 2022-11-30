package com.backend.fitchallenge.domain.member.repository;

import com.backend.fitchallenge.domain.challenge.dto.request.RankingCondition;
import com.backend.fitchallenge.domain.challenge.dto.request.RankingDto;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.post.entity.Post;

import com.querydsl.core.Tuple;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface MemberRepositoryCustom {

    List<RankingDto> rankingList(RankingCondition condition, Pageable pageable);

    List<Member> findMemberList(List<Long> memberIds);

    Member findOpponent(Long memberId);

    Tuple findOpponentIdAndChallengeId(Long memberId);

    public List<Post> findList(Long lastPostId, Long memberId , Pageable pageable);

    Long pagingCount(RankingCondition rankingCondition, Pageable pageable);

    List<Tuple> findMemberRecords();

}
