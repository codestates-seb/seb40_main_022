package com.backend.fitchallenge.domain.challenge.entity;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.notification.entity.Notification;
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

/*

CHALLENGE_ID  	CREATED_AT  	LAST_MODIFIED_AT  	APPLICANT_ID  	CHALLENGE_END  	CHALLENGE_START  	CHALLENGE_STATUS  	COUNTERPART_ID

 APPLICANT_ID, COUNTERPART_ID -> member id

SELECT
 CHALLENGE_ID, APPLICANT_ID, COUNTERPART_ID
FROM CHALLENGE;


SELECT
 START_TIME, VOLUME, END_TIME, MEMBER_ID
FROM RECORED;


-- applicant_id
SELECT chg.CHALLENGE_ID, chg.APPLICANT_ID AS MEMBER_ID, chg.CHALLENGE_STATUS rcd.START_TIME, rcd.VOLUME, rcd.END_TIME
FROM CHALLENGE AS chg
LEFT JOIN RECORD AS rcd ON chg.APPLICANT_ID = rcd.MEMBER_ID
WHERE chg.CHALLENGE_STATUS = 'ONGOING'

UNION ALL

-- counterpart_id
SELECT chg.CHALLENGE_ID, chg.COUNTERPART_ID AS MEMBER_ID, rcd.START_TIME, rcd.VOLUME, rcd.END_TIME
FROM CHALLENGE AS chg
LEFT JOIN RECORD AS rcd ON chg.COUNTERPART_ID = rcd.MEMBER_ID





WITH TEST AS (
SELECT chg.CHALLENGE_ID, chg.APPLICANT_ID AS MEMBER_ID, rcd.START_TIME, rcd.VOLUME, rcd.END_TIME
FROM CHALLENGE AS chg
LEFT JOIN RECORD AS rcd ON chg.APPLICANT_ID = rcd.MEMBER_ID

UNION ALL

-- counterpart_id
SELECT chg.CHALLENGE_ID, chg.COUNTERPART_ID AS MEMBER_ID, rcd.START_TIME, rcd.VOLUME, rcd.END_TIME
FROM CHALLENGE AS chg
LEFT JOIN RECORD AS rcd ON chg.COUNTERPART_ID = rcd.MEMBER_ID)
SELECT * FROM TEST
WHERE WHRJS S
*/