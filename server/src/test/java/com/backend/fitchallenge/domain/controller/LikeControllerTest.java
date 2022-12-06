package com.backend.fitchallenge.domain.controller;

import com.backend.fitchallenge.domain.config.TestMemberDetailService;
import com.backend.fitchallenge.domain.like.service.LikeService;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LikeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @MockBean
    private LikeService likeService;


    private final TestMemberDetailService testMemberDetailService = new TestMemberDetailService();

    private MemberDetails memberDetails;

    @BeforeAll
    public void setup() {
        Member member = Member.createBuilder()
                .email(TestMemberDetailService.UserName)
                .username("하울")
                .password("123456")
                .build();

        memberRepository.save(member);

        memberDetails = testMemberDetailService.loadUserByUsername(TestMemberDetailService.UserName);

    }

    @Test
    @DisplayName("좋아요")
    void up() throws Exception {
        //given
        Long postId = 1L;

        willDoNothing().given(likeService).up(Mockito.anyLong(), Mockito.anyLong());
        //when
         mockMvc.perform(
                post("/dailyPosts/{id}/like", postId)
                        .with(user(memberDetails)))
                        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("좋아요 취소")
    void cancel() throws Exception {
        //given
        Long postId = 1L;

        willDoNothing().given(likeService).cancel(Mockito.anyLong(), Mockito.anyLong());
        //when
       mockMvc.perform(
                delete("/dailyPosts/{id}/like/undo", postId)
                        .with(user(memberDetails)))
               .andExpect(status().isOk());
    }
}