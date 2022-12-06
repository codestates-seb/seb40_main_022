package com.backend.fitchallenge.domain.controller;

import com.backend.fitchallenge.domain.challenge.dto.request.RankingCondition;
import com.backend.fitchallenge.domain.challenge.dto.response.ChallengeResponse;
import com.backend.fitchallenge.domain.challenge.dto.response.MultiRankingResponse;
import com.backend.fitchallenge.domain.challenge.dto.response.RankingResponse;
import com.backend.fitchallenge.domain.challenge.service.ChallengeService;
import com.backend.fitchallenge.domain.config.TestMemberDetailService;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.backend.fitchallenge.domain.util.Builder.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChallengeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @MockBean
    private ChallengeService challengeService;

    @Autowired
    private Gson gson;

    private final TestMemberDetailService testMemberDetailService = new TestMemberDetailService();

    private MemberDetails memberDetails;

    private static final String USER_DEFAULT_IMAGE = "imagesForS3Test/botobo-default-profile.png";

    @BeforeAll
    public void setup() {

        Member member1 = dummyMember("abc@gmail.com", "페트르", "1234");

        Member member2 = dummyMember("bcd@gmail.com", "하울", "1233");

        memberRepository.save(member1);
        memberRepository.save(member2);

        memberDetails = testMemberDetailService.loadUserByUsername(TestMemberDetailService.UserName);

    }

    @Test
    void suggest() throws Exception {
        //given

        given(challengeService.suggest(Mockito.anyLong(), Mockito.anyLong()))
                .willReturn(1L);
        //when & then
        mockMvc.perform(
                                 post("/challenge")
                                .param("counterpartId", "2")
                                .with(user(memberDetails)))
                                .andExpect(status().isCreated());
    }

    @Test
    void getList() throws Exception {
        //given
        List<RankingResponse> responses = Arrays.asList(
                dummyRanking(2L,"페트르",USER_DEFAULT_IMAGE,180,75,21,true,LocalDate.now().plusDays(5)),
                dummyRanking(3L,"프랭크",USER_DEFAULT_IMAGE,172,71,11,false,LocalDate.now().plusDays(3)),
                dummyRanking(4L,"니체",USER_DEFAULT_IMAGE,185,89,35,true,LocalDate.now().plusDays(2)));

        PageImpl<RankingResponse> responsePage = new PageImpl<>(responses, PageRequest.of(0, 10), 3);

        given(challengeService.search(Mockito.anyLong(), Mockito.any(RankingCondition.class), Mockito.any(PageRequest.class)))
                .willReturn(MultiRankingResponse.of(responsePage,false));
        //when
        ResultActions actions = mockMvc.perform(
                get("/challenge")
                        .with(user(memberDetails))
                        .accept(MediaType.APPLICATION_JSON)
                        .param("page", "0")
        );

        MvcResult result = actions.andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    void challengeDetails() throws Exception {
        //given
        Long challengeId = 1L;
        ChallengeResponse response = dummyChallenge(1L, "하울", USER_DEFAULT_IMAGE, 180, 75,
                2L, "페트르", USER_DEFAULT_IMAGE, 178, 71);

        given(challengeService.getChallenge(Mockito.any(), Mockito.anyLong()))
                .willReturn(response);
        //when
        ResultActions actions = mockMvc.perform(
                get("/challenge/{id}", challengeId)
                        .with(user(memberDetails))
                        .accept(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.applicantName").value(response.getApplicantName()))
                .andExpect(jsonPath("$.counterpartName").value(response.getCounterpartName()));
    }

    @Test
    void refuse() throws Exception {
        //given
        Long challengeId = 1L;

        willDoNothing().given(challengeService).refuse(Mockito.anyLong(), Mockito.anyLong());

        //when & then
        mockMvc.perform(
                        delete("/challenge/{id}", challengeId)
                                .with(user(memberDetails)))
                .andExpect(status().isOk());
    }

    @Test
    void suspend() throws Exception {
        //given
        Long challengeId = 1L;

        given(challengeService.suspend(Mockito.anyLong(), Mockito.anyLong()))
                .willReturn(1L);
        //when & then
        mockMvc.perform(
                        delete("/challenge/{id}/suspend", challengeId)
                                .with(user(memberDetails)))
                .andExpect(status().isOk());

    }

    @Test
    void accept() throws Exception {
        //given
        Long challengeId = 1L;

        willDoNothing().given(challengeService).accept(Mockito.anyLong(), Mockito.anyLong());
        //when & then
        mockMvc.perform(
                        post("/challenge/{id}/accept", challengeId)
                                .with(user(memberDetails)))
                .andExpect(status().isOk());
    }
}