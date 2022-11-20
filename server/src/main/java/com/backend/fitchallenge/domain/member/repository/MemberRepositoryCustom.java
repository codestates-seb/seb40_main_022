package com.backend.fitchallenge.domain.member.repository;

import com.backend.fitchallenge.domain.challenge.dto.RankingCondition;
import com.backend.fitchallenge.domain.challenge.dto.RankingDto;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepositoryCustom {

    List<RankingDto> rankingList(RankingCondition condition);

    List<Member> findMemberList(List<Long> memberIds);
}
