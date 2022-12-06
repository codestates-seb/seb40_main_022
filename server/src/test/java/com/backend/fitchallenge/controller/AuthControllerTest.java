package com.backend.fitchallenge.controller;

import com.backend.fitchallenge.builder.MemberRelatedBuilder;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.member.service.AuthService;

import com.backend.fitchallenge.global.dto.request.LoginDto;
import com.backend.fitchallenge.global.security.filter.JwtAuthenticationFilter;
import com.backend.fitchallenge.global.security.filter.JwtVerificationFilter;
import com.backend.fitchallenge.global.security.jwt.JwtTokenProvider;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import com.backend.fitchallenge.global.security.userdetails.MemberDetailsService;
import com.backend.fitchallenge.util.TestMemberUtil;
import com.google.gson.Gson;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;

import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultActions;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Optional;

import static com.backend.fitchallenge.builder.MemberRelatedBuilder.dummyMemberDetails;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest extends TestMemberUtil {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private MemberRepository memberRepository;
    @MockBean
    private AuthService authService;



    //fixme : 필터는 어찌 처리할 수 있을까.
    @Test
    @DisplayName(" 로그인 테스트")
    public void logInTest() throws Exception {
        //given
        LoginDto loginDto = MemberRelatedBuilder.dummyLoginDto(EMAIL, PASSWORD);
        String content = gson.toJson(loginDto);
        MemberDetails memberDetails =
                dummyMemberDetails(1L, EMAIL, PASSWORD, AUTHORITY);
        Authentication authentication = new UsernamePasswordAuthenticationToken(memberDetails, null, memberDetails.getAuthorities());

        given(jwtAuthenticationFilter.attemptAuthentication(Mockito.any(HttpServletRequest.class)
        ,Mockito.any(HttpServletResponse.class)))
                .willReturn(authentication);
        given(memberRepository.findByEmail(Mockito.anyString()))
                .willReturn(Optional.of(memberDetails));
        //when
        ResultActions actions = mockMvc.perform(
                post("/members/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );
        //then
        actions.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("컨트롤러 로그아웃 테스트")
    public void logoutTest() throws Exception{
        //given
        String accessToken = ACCESS_TOKEN;
        String refreshToken = REFRESH_TOKEN;

        //when
        ResultActions actions = mockMvc.perform(
                        delete("/member/logout")
                                .header("Authorization", "Bearer " + accessToken)
                                .header("RefreshToken", refreshToken)
                                .accept(MediaType.APPLICATION_JSON)
        );
        //then
        actions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("컨트롤러 리이슈 테스트")
    public void reIssueTest() throws Exception{
        //given
        String newAccessToken = NEW_ACCESS_TOKEN;
        String newRefreshToken = NEW_REFRESH_TOKEN;

        given(authService.reissueAccessToken(Mockito.anyString(),Mockito.anyString()))
                .willReturn(newAccessToken);
        given(authService.reissueRefreshToken(Mockito.anyString()))
                .willReturn(newRefreshToken);

        //when
        ResultActions actions = mockMvc.perform(
                get("/member/reissue")
                        .header("Authorization", "Bearer " + newAccessToken)
                        .header("RefreshToken", newRefreshToken)
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        actions.andExpect(status().isOk());
    }
}
