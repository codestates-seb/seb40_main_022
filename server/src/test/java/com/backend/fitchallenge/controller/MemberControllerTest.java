package com.backend.fitchallenge.controller;

import com.backend.fitchallenge.domain.member.dto.request.MemberCreate;
import com.backend.fitchallenge.domain.member.dto.request.MemberUpdateVO;
import com.backend.fitchallenge.domain.member.dto.response.DetailsMemberResponse;
import com.backend.fitchallenge.domain.member.dto.response.UpdateResponse;
import com.backend.fitchallenge.domain.member.dto.response.extract.DailyPost;
import com.backend.fitchallenge.domain.member.dto.response.extract.ExtractActivity;
import com.backend.fitchallenge.domain.member.dto.response.extract.ExtractMember;
import com.backend.fitchallenge.domain.member.service.MemberService;
import com.backend.fitchallenge.global.dto.response.SliceMultiResponse;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import com.backend.fitchallenge.util.TestMemberUtil;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.filter.DelegatingFilterProxy;

import static com.backend.fitchallenge.builder.MemberRelatedBuilder.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest extends TestMemberUtil {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private DelegatingFilterProxy delegatingFilterProxy; // 있을때와 없을때 회원정보수정 차이
    @MockBean
    private MemberService memberService;


    @Test
    @DisplayName("회원가입 테스트")
    void signUpTest() throws Exception{
        //given
        MemberCreate memberCreate = dummyMemberCreate(EMAIL,PASSWORD,USERNAME);
        Long memberId = 1L;

        given(memberService.createMember(Mockito.any(MemberCreate.class)))
                .willReturn(memberId);

        String content = gson.toJson(memberCreate);

        //when
        ResultActions actions =
                mockMvc.perform(
                        post("/members/signup")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        //then
        actions
            .andExpect(status().isCreated())
            .andDo(print());
    }

    //fixme : 방법론 알아보기.
    @Test
    @DisplayName("회원정보수정 테스트 : 이미지 등록")
    void patchTest() throws Exception{
        //given
        MemberDetails memberDetails =
                dummyMemberDetails(1L, EMAIL, PASSWORD, AUTHORITY);
        String accessToken = ACCESS_TOKEN;
        String refreshToken = REFRESH_TOKEN;

        MockMultipartFile multipartFile = FILE;

        UpdateResponse updateResponse =
                dummyUpdaterResponse(PASSWORD, USERNAME, JOB, ADDRESS, GENDER, AGE, HEIGHT
                ,WEIGHT, KILOGRAM, SPLIT, IMAGE_PATH);

        given(memberService.updateMember(Mockito.anyString(), Mockito.any(MemberUpdateVO.class)))
                .willReturn(updateResponse);

        //when
        ResultActions actions = mockMvc.perform(
                multipart("/members/myPage")
                        .file(multipartFile)
                        .param("password", PASSWORD)
                        .param("username", USERNAME)
                        .param("job", JOB)
                        .param("address", ADDRESS)
                        .param("gender", GENDER)
                        .param("age", AGE.toString())
                        .param("height", HEIGHT.toString())
                        .param("weight", WEIGHT.toString())
                        .param("kilogram", KILOGRAM.toString())
                        .param("split", SPLIT.toString())
                        .header("Authorization", "Bearer " + accessToken)
                        .header("RefreshToken", refreshToken)
                        .with(user(memberDetails))

        );

        //then
        actions.andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @DisplayName("마이페이지 조회 테스트")
    void myInfoDetails() throws Exception{
        //given
        MemberDetails memberDetails =
                dummyMemberDetails(1L, EMAIL, PASSWORD, AUTHORITY);
        String accessToken = ACCESS_TOKEN;
        String refreshToken = REFRESH_TOKEN;

        ExtractMember extractMember = dummyExtractMember(USERNAME, IMAGE_PATH, HEIGHT, WEIGHT);
        ExtractActivity extractActivity = dummyExtractActivity(KILOGRAM, DAY_COUNT, POINT);
        DailyPost dailyPost = dummyDailyPost(1L, IMAGE_PATH);
        SliceMultiResponse<DailyPost> postResult = dummySliceDailyPost(dailyPost);

        DetailsMemberResponse detailsMemberResponse =
                DetailsMemberResponse.of(extractMember, extractActivity, postResult, 1);

        given(memberService.getMyInfo(Mockito.any(Long.class), Mockito.any(String.class), Mockito.any(Pageable.class)))
                .willReturn(detailsMemberResponse);

        //when
        ResultActions actions = mockMvc.perform(
                get("/members/myPage")
                        .header("Authorization", "Bearer " + accessToken)
                        .header("RefreshToken", refreshToken)
                        .with(user(memberDetails))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        actions.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("특정 유저 조회 테스트")
    void memberDetails() throws Exception{

        //given
        ExtractMember extractMember = dummyExtractMember(USERNAME, IMAGE_PATH, HEIGHT, WEIGHT);
        ExtractActivity extractActivity = dummyExtractActivity(KILOGRAM, DAY_COUNT, POINT);
        DailyPost dailyPost = dummyDailyPost(1L, IMAGE_PATH);
        SliceMultiResponse<DailyPost> postResult = dummySliceDailyPost(dailyPost);

        DetailsMemberResponse detailsMemberResponse =
                DetailsMemberResponse.of(extractMember, extractActivity, postResult, 1);

        given(memberService.getMember(Mockito.any(Long.class), Mockito.any(Long.class), Mockito.any(Pageable.class)))
                .willReturn(detailsMemberResponse);

        //when
        ResultActions actions = mockMvc.perform(
                get("/members/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        actions.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("회원 탈퇴 테스트")
    void DeleteMember() throws Exception{
        //given
        MemberDetails memberDetails = dummyMemberDetails(1L, EMAIL, PASSWORD, AUTHORITY);
        String accessToken = ACCESS_TOKEN;
        String refreshToken = REFRESH_TOKEN;
        Long memberId = 1L;

        given(memberService.deleteMember(Mockito.anyString(), Mockito.anyString()))
                .willReturn(memberId);

        //when
        ResultActions actions = mockMvc.perform(
                delete("/members/myPage/delete")
                        .header("Authorization", "Bearer " + accessToken)
                        .header("RefreshToken", refreshToken)
                        .with(user(memberDetails))
        );

        //then
        actions.andExpect(status().isOk());
    }
}
