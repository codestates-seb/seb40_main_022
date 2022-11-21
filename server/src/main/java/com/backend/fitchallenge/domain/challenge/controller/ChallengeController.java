package com.backend.fitchallenge.domain.challenge.controller;

import com.backend.fitchallenge.domain.challenge.dto.RankingCondition;
import com.backend.fitchallenge.domain.challenge.service.ChallengeService;
import com.backend.fitchallenge.global.annotation.AuthMember;

import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge")
@Slf4j
public class ChallengeController {

    private final ChallengeService challengeService;

    /**
     * 챌린지 제안
     * @param memberDetails 로그인 세션 정보
     * @param counterpartId 상대방 id
     * @return
     */
    @PostMapping
    public ResponseEntity<?> suggest(
            @AuthMember MemberDetails memberDetails,
            @RequestParam Long counterpartId) {

        Long result = challengeService.suggest(memberDetails.getMemberId(), counterpartId);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * 랭킹 페이지
     * @param pageable  페이지네이션 프론트와 추후논의 필요
     * @param condition 검색 필터 조건 - 분할, 키, 몸무게, 경력
     */
    @GetMapping
    public ResponseEntity<?> getList(
            @PageableDefault(size =10)Pageable pageable, RankingCondition condition) {

        log.info("Condition.split = {}", condition.getSplit());

        return new ResponseEntity<>(challengeService.search(condition), HttpStatus.OK);
    }

    /**
     * 챌린지 거절
     * @param challengeId 해당 챌린지 Id
     */
    @DeleteMapping
    public ResponseEntity<?> refuse(
            @AuthMember MemberDetails memberDetails,
            @RequestParam Long challengeId) {
        challengeService.refuse(memberDetails.getMemberId(), challengeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * 챌린지 중단
     * @param challengeId 해당 챌린지 Id
     */
    @DeleteMapping("/suspend")
    public ResponseEntity<?> suspend(
        @AuthMember MemberDetails memberDetails,
        @RequestParam Long challengeId) {
        challengeService.suspend(memberDetails.getMemberId(), challengeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * 챌린지 수락
     * @param challengeId 해당 챌린지 Id
     */
    @PostMapping("/accept")
    public ResponseEntity<?> accept(
            @AuthMember MemberDetails memberDetails,
            @RequestParam Long challengeId) {
        challengeService.accept(memberDetails.getMemberId(), challengeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
