package com.backend.fitchallenge.domain.challenge.service;

import com.backend.fitchallenge.domain.challenge.dto.RankingCondition;
import com.backend.fitchallenge.domain.challenge.dto.RankingDto;
import com.backend.fitchallenge.domain.challenge.entity.Challenge;
import com.backend.fitchallenge.domain.challenge.exception.CannotAcceptChallenge;
import com.backend.fitchallenge.domain.challenge.exception.CannotRefuseChallenge;
import com.backend.fitchallenge.domain.challenge.exception.CannotSuspendChallenge;
import com.backend.fitchallenge.domain.challenge.exception.ChallengeNotFound;
import com.backend.fitchallenge.domain.challenge.repository.ChallengeRepository;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChallengeService {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final ChallengeRepository challengeRepository;

    /**
     * 챌린지 제안
     * 상대방에게 챌린지 제안 알림 서비스 추가 필요
     * 나와 상대방이 챌린지 진행중이 아닌 상태에대한 검증은 프론트에서 해결
     * @return
     */
    public Long suggest(Long memberId, Long counterpartId) {

        Challenge challenge = challengeRepository.save(Challenge.toEntity(memberId,counterpartId));

        return challenge.getId();
    }

    /**
     * 랭킹 목록 조회
     */
    public List<RankingDto> search(RankingCondition condition) {
        return memberRepository.rankingList(condition);
    }


    /**
     * 챌린지 수락
     * 로그인 유저가 counterpart인경우만가능
     * 로그인 유저가 현재 챌린지를 진행중인지 체크
     * start()메서드 - 챌린지 상태를 OnGoing으로 바꾸고 각 member에게 챌린지 추가
     * 챌린저들이 이전에 제안을 하거나 받은 제안들 삭제
     * 챌린지  시작, 끝나는 날짜 설정
     * 챌린지 수락시 알림 필요
     */
    public String accept(Long memberId, Long challengeId) {

        Challenge challenge = findChallenge(challengeId);
        //로그인 유저가 counterpart인경우만 수락가능
        Member counterpart = memberService.findVerifiedMemberById(memberId);
        Member applicant = memberService.findVerifiedMemberById(challenge.getApplicantId());


        if (challenge.getCounterpartId() == memberId && isEmpty(counterpart.getChallenge())) {
            log.info("challenge_applicant={}", applicant.getUsername());
            log.info("challenge_counterpart={}", counterpart.getUsername());
            challenge.start(applicant, counterpart);
            List<Long> memberIds = List.of(challenge.getApplicantId(), memberId);
            challengeRepository.deleteSuggest(memberIds); // 챌린저들의 이전의 제안하거나 받은 제안들 삭제
            return challenge.setChallengeTerm();
            //수락도 알림 필요
        } else {
            throw new CannotAcceptChallenge();
        }

    }

    /**
     * 챌린지 거절
     * 로그인 유저가 counterpart인경우만가능
     * 로그인 유저가 현재 챌린지를 진행중인지 체크
     * DB에서 해당 챌린지 삭제
     */
    public void refuse(Long memberId, Long challengeId) {
        Challenge challenge = findChallenge(challengeId);
        Member member = memberService.findVerifiedMemberById(memberId);
        //로그인 유저가 counterpart인경우만 거절가능
        if (challenge.getCounterpartId() == memberId && isEmpty(member.getChallenge())) {
            challengeRepository.delete(challenge);
        } else {
            throw new CannotRefuseChallenge();
        }
    }

    /**
     *챌린지 중단
     * 로그인유저가 applicant,counterpart 둘중 하나여야한다.
     * 챌린지 승락후 당일이내에 취소가능 -페널티 없음
     * 챌린지 시작후 3일이 지나지 않았으면 중단 불가
     * 챌린지 시작 3일후 중도포기시 페널티 부여 - 상세내용은 논의 필요
     */
    public void suspend(Long memberId, Long challengeId) {

        Challenge challenge = findChallenge(challengeId);

        log.info("applicant={}", challenge.getApplicantId());
        log.info("counter={}", challenge.getCounterpartId());

        if (memberId != challenge.getApplicantId() && memberId != challenge.getCounterpartId() ) {
            throw new CannotSuspendChallenge();
        }

        if (LocalDate.now() == challenge.getModifiedAt().toLocalDate()) {

            memberService.findVerifiedMemberById(challenge.getApplicantId()).suspendChallenge();
            memberService.findVerifiedMemberById(challenge.getCounterpartId()).suspendChallenge();

            challengeRepository.delete(challenge);
        }

//         시작후 3일안됬으면 중지안된다
        if (LocalDate.now().isBefore(challenge.getChallengeStart().plusDays(3))) {
            throw new CannotSuspendChallenge();
        }


            //시작 후 3일 이후의 중도포기
            memberService.findVerifiedMemberById(challenge.getApplicantId()).suspendChallenge();
           memberService.findVerifiedMemberById(challenge.getCounterpartId()).suspendChallenge();

            challengeRepository.delete(challenge);

            //memberPoint 감소
            //member authorities Black으로 변경

    }

    private Challenge findChallenge(Long challengeId) {
        return challengeRepository.findById(challengeId).orElseThrow(ChallengeNotFound::new);
    }


}
