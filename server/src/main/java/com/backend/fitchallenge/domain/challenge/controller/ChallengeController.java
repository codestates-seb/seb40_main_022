package com.backend.fitchallenge.domain.challenge.controller;

import com.backend.fitchallenge.domain.challenge.dto.ChallengeResponse;
import com.backend.fitchallenge.domain.challenge.dto.PageRequest;
import com.backend.fitchallenge.domain.challenge.dto.RankingCondition;
import com.backend.fitchallenge.domain.challenge.entity.Challenge;
import com.backend.fitchallenge.domain.challenge.service.ChallengeService;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.global.annotation.AuthMember;

import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge")
public class ChallengeController {

    private final ChallengeService challengeService;

    @PostMapping
    public ResponseEntity<?> suggest(
            @AuthMember MemberDetails memberDetails,
            @RequestParam Long counterpartId) {

        ChallengeResponse response = challengeService.suggest(memberDetails.getMemberId(), counterpartId);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getList(
            @PageableDefault(size =10)Pageable pageable, RankingCondition condition) {
        return new ResponseEntity<>(challengeService.search(condition), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> refuse(
            @AuthMember MemberDetails memberDetails,
            @RequestParam Long challengeId) {
        challengeService.refuse(memberDetails.getMemberId(), challengeId);

        return null;
    }
}
