package com.backend.fitchallenge.domain.member.repository;

import com.backend.fitchallenge.domain.challenge.dto.request.RankingCondition;
import com.backend.fitchallenge.domain.challenge.dto.request.RankingDto;
import com.backend.fitchallenge.domain.member.entity.Member;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface MemberRepositoryCustom {

    List<RankingDto> rankingList(RankingCondition condition, Pageable pageable);

    List<Member> findMemberList(List<Long> memberIds);

    Long pagingCount(RankingCondition rankingCondition, Pageable pageable);
}
