package com.backend.fitchallenge.domain.challenge.controller;

import com.backend.fitchallenge.domain.challenge.dto.request.PageRequest;
import com.backend.fitchallenge.domain.challenge.dto.request.RankingCondition;
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

    @PostMapping
    public ResponseEntity<?> suggest(
            @AuthMember MemberDetails memberDetails,
            @RequestParam Long counterpartId) {

        Long result = challengeService.suggest(memberDetails.getMemberId(), counterpartId);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> challengeDetails(
            @AuthMember MemberDetails memberDetails,
            @PathVariable Long id) {

        return new ResponseEntity<>(challengeService.getChallenge(memberDetails.getMemberId(), id), HttpStatus.OK);

    }

    /**
     * 랭킹 페이지
     * @param condition 검색 필터 조건 - 분할, 키, 몸무게, 경력
     */
    @GetMapping
    public ResponseEntity<?> getList(
            @AuthMember MemberDetails memberDetails,
            PageRequest pageRequest, RankingCondition condition) {

        log.info("Condition.split = {}", condition.getSplit());

        if(memberDetails == null){
            return new ResponseEntity<>(challengeService.searchWithoutLogin(condition,pageRequest.of()), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(challengeService.search(memberDetails.getMemberId(), condition,pageRequest.of()), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> refuse(
            @AuthMember MemberDetails memberDetails,
            @PathVariable Long id) {
        challengeService.refuse(memberDetails.getMemberId(), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/suspend")
    public ResponseEntity<?> suspend(
        @AuthMember MemberDetails memberDetails,
        @PathVariable Long id) {
        Long challengeId = challengeService.suspend(memberDetails.getMemberId(), id);
        return new ResponseEntity<>(challengeId,HttpStatus.OK);
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<?> accept(
            @AuthMember MemberDetails memberDetails,
            @PathVariable Long id) {
        challengeService.accept(memberDetails.getMemberId(), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
