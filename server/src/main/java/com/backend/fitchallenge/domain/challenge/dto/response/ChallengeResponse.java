package com.backend.fitchallenge.domain.challenge.dto.response;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.entity.ProfileImage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeResponse {

    private Long applicantId;
    private String applicantName;
    private ProfileImage applicantImage;
    private String applicantImage;
    private Integer applicantHeight;
    private Integer applicantWeight;

    private Long counterpartId;
    private String counterpartName;
    private ProfileImage counterpartImage;
    private String counterpartImage;
    private Integer counterpartHeight;
    private Integer counterpartWeight;

@Builder
    public ChallengeResponse(Long applicantId, String applicantName, String applicantImage, Integer applicantHeight, Integer applicantWeight,
                             Long counterpartId, String counterpartName, String counterpartImage, Integer counterpartHeight, Integer counterpartWeight) {
        this.applicantId = applicantId;
        this.applicantName = applicantName;
        this.applicantImage = applicantImage;
        this.applicantHeight = applicantHeight;
        this.applicantWeight = applicantWeight;
        this.counterpartId = counterpartId;
        this.counterpartName = counterpartName;
        this.counterpartImage = counterpartImage;
        this.counterpartHeight = counterpartHeight;
        this.counterpartWeight = counterpartWeight;
    }

    public static ChallengeResponse of(Member applicant,  Member counterpart) {
        return ChallengeResponse.builder()
                .applicantId(applicant.getId())
                .applicantName(applicant.getUsername())
                .applicantImage(applicant.getProfileImage().getPath())
                .applicantHeight(applicant.getHeight())
                .applicantWeight(applicant.getWeight())
                .counterpartId(counterpart.getId())
                .counterpartName(counterpart.getUsername())
                .counterpartImage(counterpart.getProfileImage().getPath())
                .counterpartHeight(counterpart.getHeight())
                .counterpartWeight(counterpart.getWeight())
                .build();
    }
}
