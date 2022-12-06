package com.backend.fitchallenge.domain.service;

import com.backend.fitchallenge.domain.challenge.dto.request.RankingCondition;
import com.backend.fitchallenge.domain.challenge.dto.request.RankingDto;
import com.backend.fitchallenge.domain.challenge.dto.response.ChallengeResponse;
import com.backend.fitchallenge.domain.challenge.entity.Challenge;
import com.backend.fitchallenge.domain.challenge.repository.ChallengeRepository;
import com.backend.fitchallenge.domain.challenge.service.ChallengeService;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.entity.ProfileImage;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.member.service.MemberService;
import com.backend.fitchallenge.domain.notification.service.NotificationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
@SpringBootTest
class ChallengeServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private NotificationService notificationService;

    private static final String USER_DEFAULT_IMAGE = "imagesForS3Test/botobo-default-profile.png";


    private Challenge challenge;

  private  Member member1;
  private  Member member2;

//  @Sql({"/db/h2/data.sql"})
    @BeforeAll
    public void setup() throws Exception {

//        try (Connection conn = dataSource.getConnection()) {
//            ScriptUtils.executeSqlScript(conn, new ClassPathResource("/db/h2/data.sql"));
//        }

        ProfileImage profileImage1 = ProfileImage.builder()
                .path(USER_DEFAULT_IMAGE)
                .member(member1)
                .build();

        member1 = Member.createBuilder()
                .email("abc@gmail.com")
                .username("하울")
                .password("123456")
                .build();

         member2 = Member.createBuilder()
                .email("def@gmail.com")
                .username("페트르")
                .password("123456")
                 .profileImage(profileImage1)
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);




        challenge = Challenge.builder()
                .applicantId(1L)
                .counterpartId(2L)
                .challengeStatus(Challenge.ChallengeStatus.SUGGESTED)
                .build();

        challengeRepository.save(challenge);
    }




    @Test
    void getChallenge() {

        //when
        ChallengeResponse result = challengeService.getChallenge(2L, 1L);

        //then
        assertThat(result.getApplicantName()).isEqualTo("하울");
        assertThat(result.getApplicantName()).isEqualTo("페트르");

    }

    @Test
    void search() {
        //given
        RankingCondition condition = new RankingCondition(null, 160, 190, 50, 100, null, null);

        List<RankingDto> rankingDtos = Arrays.asList(
                new RankingDto(member1.getId(), member1.getUsername(), member1.getProfileImage().getPath(), member1.getHeight(), member1.getWeight(), member1.getMemberActivity().getPoint(), member1.getPeriod(), member1.getChallenge().getId()),
                new RankingDto(member2.getId(), member2.getUsername(), member2.getProfileImage().getPath(), member2.getHeight(), member2.getWeight(), member2.getMemberActivity().getPoint(), member2.getPeriod(), member2.getChallenge().getId())
        );

        given(memberService.findVerifiedMemberById(Mockito.anyLong()))
                .willReturn(member1);

        given(memberRepository.pagingCount(Mockito.any(RankingCondition.class), Mockito.any(PageRequest.class)))
                .willReturn(2L);

        given(memberRepository.rankingList(Mockito.any(RankingCondition.class), Mockito.any(PageRequest.class)))
                .willReturn(rankingDtos);

        challengeService.search(1L, condition, PageRequest.of(0, 10));

    }

    @Test
    void accept() {
        //given
        given(challengeRepository.findById(Mockito.anyLong()))
                .willReturn(Optional.ofNullable(challenge));

        willDoNothing().given(challengeRepository).deleteSuggest(Mockito.anyList());
        willDoNothing().given(notificationService).send(Mockito.any(Member.class), Mockito.any(Challenge.class), Mockito.anyString());

        //when
        challengeService.accept(2L, 1L);

        //then
        assertThat(challenge.getChallengeStatus()).isEqualTo(Challenge.ChallengeStatus.ONGOING);
    }

    @Test
    void refuse() {
        //given
        given(challengeRepository.findById(Mockito.anyLong()))
                .willReturn(Optional.ofNullable(challenge));

        willDoNothing().given(notificationService).send(Mockito.any(Member.class), Mockito.any(Challenge.class), Mockito.anyString());

        willDoNothing().given(challengeRepository).delete(Mockito.any(Challenge.class));

        //when
        challengeService.refuse(2L,1L);
        //then
        assertNull(challenge.getApplicantId());
    }

    @Test
    void suspend() {
//        given(challengeRepository.findById(Mockito.anyLong()))
//                .willReturn(Optional.ofNullable(challenge));

    }
}