package com.backend.fitchallenge.domain.challenge.service;

import com.backend.fitchallenge.domain.challenge.dto.ChallengeResponse;
import com.backend.fitchallenge.domain.challenge.dto.RankingCondition;
import com.backend.fitchallenge.domain.challenge.dto.RankingDto;
import com.backend.fitchallenge.domain.challenge.entity.Challenge;
import com.backend.fitchallenge.domain.challenge.exception.CannotRefuseChallenge;
import com.backend.fitchallenge.domain.challenge.exception.ChallengeNotFound;
import com.backend.fitchallenge.domain.challenge.repository.ChallengeRepository;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ChallengeService {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final ChallengeRepository challengeRepository;


    public ChallengeResponse suggest(Long memberId, Long counterpartId) {
        List<Member> challengers = new ArrayList<>();

        Member applicant = memberService.findVerifiedMemberById(memberId);
        Member counterPart = memberService.findVerifiedMemberById(counterpartId);

        challengers.add(applicant);
        challengers.add(counterPart);

        Challenge challenge = challengeRepository.save(Challenge.of(challengers));

        List<String> memberNames = challenge.getMembers().stream()
                .map(member -> member.getUsername()).collect(Collectors.toList());

        return ChallengeResponse.toResponse(challenge.getId(), memberNames);
    }

    public List<RankingDto> search(RankingCondition condition) {
        return memberRepository.rankingList(condition);
    }


    public void refuse(Long memberId, Long challengeId) {

        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(ChallengeNotFound::new);
        Member member = memberService.findVerifiedMemberById(memberId);

        if (challenge.getMembers().contains(member)) {
            challengeRepository.delete(challenge);
        } else{
            throw new CannotRefuseChallenge();
        }

    }
}
