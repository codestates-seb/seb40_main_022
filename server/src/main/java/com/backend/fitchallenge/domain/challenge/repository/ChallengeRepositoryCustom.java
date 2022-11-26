package com.backend.fitchallenge.domain.challenge.repository;

import com.backend.fitchallenge.domain.challenge.entity.Challenge;
import com.querydsl.core.Tuple;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepositoryCustom {

    void deleteSuggest(List<Long> memberIds);

    List<Challenge> ongoingChallenges();
}
