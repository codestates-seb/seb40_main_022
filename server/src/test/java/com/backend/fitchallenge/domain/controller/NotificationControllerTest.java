package com.backend.fitchallenge.domain.controller;

import com.backend.fitchallenge.domain.config.TestMemberDetailService;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.notification.dto.MultiNotificationResponse;
import com.backend.fitchallenge.domain.notification.dto.NotificationResponse;
import com.backend.fitchallenge.domain.notification.service.NotificationService;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.backend.fitchallenge.domain.util.Builder.dummyNotification;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @MockBean
    private NotificationService notificationService;

    @Autowired
    private Gson gson;

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
    @DisplayName("sse연결")
    void connect() throws Exception {
        //given
        given(notificationService.subscribe(Mockito.anyLong(), Mockito.anyString()))
                .willReturn(new SseEmitter());
        mockMvc.perform(
                        get("/connect")
                                .with(user(memberDetails))
                                .accept(MediaType.TEXT_EVENT_STREAM)
                                .header("Last-Event-ID", "1_1631593143664"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("알림 조회")
    void notifications() throws Exception {
        //given
        List<NotificationResponse> responses = Arrays.asList(
                dummyNotification(3L,"챌린지가 중단되었습니다.","/challenge/2",LocalDateTime.now().plusDays(2),false),
                dummyNotification(2L,"챌린지가 수락되었습니다.","/challenge/2",LocalDateTime.now().plusDays(1),false),
                dummyNotification(1L,"챌린지가 신청하셨습니다.","/challenge/1",LocalDateTime.now(),true));

        given(notificationService.findAllById(Mockito.anyLong()))
            .willReturn(MultiNotificationResponse.of(responses, 2L));

        //when & then
        ResultActions actions = mockMvc.perform(
                        get("/notifications")
                                .with(user(memberDetails)))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("알림 읽음 처리")
    void readNotification() throws Exception {
        //given
        Long notificationId = 1L;

        willDoNothing().given(notificationService).readNotification(Mockito.anyLong());
        //when & then
        mockMvc.perform(
                        patch("/notifications/{id}", notificationId)
                                .with(user(memberDetails)))
                .andExpect(status().isNoContent());
    }
}