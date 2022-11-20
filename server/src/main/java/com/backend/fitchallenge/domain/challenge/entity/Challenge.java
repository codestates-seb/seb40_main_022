package com.backend.fitchallenge.domain.challenge.entity;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.global.audit.Auditable;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Challenge extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_id")
    private Long id;


    @Column(name ="applicant_id")
    private Long applicantId;

    @Column(name = "counterpart_id")
    private Long counterpartId;

    @Enumerated(value = EnumType.STRING)
    ChallengeStatus challengeStatus;

    @Column(name = "challenge_start")
    private LocalDate challengeStart;

    @Column(name = "challenge_end")
    private LocalDate challengeEnd;


    public enum ChallengeStatus {
        SUGGESTED("제안됨"),
        ONGOING("진행중");

        @Getter
        private String value;

        ChallengeStatus(String value) {
            this.value = value;
        }
    }

    @Builder
    public Challenge(Long applicantId, Long counterpartId, ChallengeStatus challengeStatus) {
        this.applicantId = applicantId;
        this.counterpartId = counterpartId;
        this.challengeStatus = challengeStatus;
    }

    public static Challenge toEntity(Long applicantId, Long counterpartId) {
        return Challenge.builder()
                .applicantId(applicantId)
                .counterpartId(counterpartId)
                .challengeStatus(ChallengeStatus.SUGGESTED)
                .build();
    }

    public void start(Member applicant, Member counterpart) {
        this.challengeStatus = ChallengeStatus.ONGOING;
        applicant.addChallenge(this);
        counterpart.addChallenge(this);
    }

    //수락후 다음날 부터 시작
    public String setChallengeTerm() {
        this.challengeStart = this.getModifiedAt().plusDays(1).toLocalDate();
        this.challengeEnd = this.challengeStart.plusDays(7);
        return this.challengeStart.toString() + " ~ " + challengeEnd.toString();
    }

}
