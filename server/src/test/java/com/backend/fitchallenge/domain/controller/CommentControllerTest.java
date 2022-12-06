package com.backend.fitchallenge.domain.controller;

import com.backend.fitchallenge.domain.config.TestMemberDetailService;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.repository.MemberRepository;
import com.backend.fitchallenge.domain.postcomment.dto.request.CommentCreate;
import com.backend.fitchallenge.domain.postcomment.dto.request.CommentGet;
import com.backend.fitchallenge.domain.postcomment.dto.request.CommentUpdate;
import com.backend.fitchallenge.domain.postcomment.dto.response.CommentResponse;
import com.backend.fitchallenge.domain.postcomment.dto.response.CommentUpdateResponse;
import com.backend.fitchallenge.domain.postcomment.service.CommentService;
import com.backend.fitchallenge.global.dto.response.SliceMultiResponse;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.SliceImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.backend.fitchallenge.domain.util.Builder.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @MockBean
    private CommentService commentService;

    @Autowired
    private Gson gson;

    private final TestMemberDetailService testMemberDetailService = new TestMemberDetailService();

    private MemberDetails memberDetails;

    private static final String USER_DEFAULT_IMAGE = "imagesForS3Test/botobo-default-profile.png";


    @BeforeAll
    public void setup() {

        Member member = dummyMember("abc@gmail.com", "페트르", "1234");

        memberRepository.save(member);

        memberDetails = testMemberDetailService.loadUserByUsername(TestMemberDetailService.UserName);

    }

    @Test
    @DisplayName("댓글 등록")
    void create() throws Exception {
        //given
        Long postId = 1L;
        CommentCreate commentCreate = new CommentCreate("테스트를 위한 코멘트");

        String content = gson.toJson(commentCreate);

        given(commentService.createComment(Mockito.anyLong(), Mockito.anyLong(), Mockito.any(CommentCreate.class)))
                .willReturn(1L);

        ResultActions actions = mockMvc.perform(
                post("/dailyPosts/{id}/comments", postId)
                        .with(user(memberDetails))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content));

        actions.andExpect(status().isCreated());

    }

    @Test
    @DisplayName("댓글 조회")
    void getList() throws Exception {
        //given
        Long postId = 1L;

        String content = gson.toJson(new CommentGet(3L));
        CommentResponse response1 = dummyCommentResponse(1L, "댓글 조회 테스트 1", 1L, "하울", USER_DEFAULT_IMAGE);
        CommentResponse response2 = dummyCommentResponse(2L, "댓글 조회 테스트 2", 1L, "하울", USER_DEFAULT_IMAGE);


        List<CommentResponse> data = new ArrayList<>();
        data.add(response1);
        data.add(response2);

        given(commentService.getCommentList(Mockito.anyLong(), Mockito.anyLong(), Mockito.any(PageRequest.class)))
                .willReturn(SliceMultiResponse.createSliceComment(new SliceImpl<>(data, PageRequest.of(0, 10), false)));

        ResultActions actions = mockMvc.perform(
                get("/dailyPosts/{id}/comments", postId)
                        .with(user(memberDetails))
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content));

        MvcResult mvcResult = actions.andExpect(status().isOk())
                .andReturn();

    }

    @Test
    @DisplayName("댓글 수정")
    void update() throws Exception {

        Long postId = 1L;
        Long commentId = 1L;


        String content = gson.toJson(new CommentUpdate("테스트 수정을 위한 댓글"));

        CommentUpdateResponse response = dummyCommentUpdate(1L, "테스트 수정을 위한 댓글");

        given(commentService.updateComment(Mockito.anyLong(), Mockito.anyLong(), Mockito.any(CommentUpdate.class)))
                .willReturn(response);

        ResultActions actions = mockMvc.perform(
                patch("/dailyPosts/{id}/comments/{comments-id}", postId, commentId)
                        .with(user(memberDetails))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content));

        actions.andExpect(status().isOk());


    }

    @Test
    @DisplayName("댓글 삭제")
    void delete() throws Exception {
        //given
        Long postId = 1L;
        Long commentId = 1L;

        willDoNothing().given(commentService).deleteComment(Mockito.anyLong(), Mockito.anyLong());
        //when
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/dailyPosts/{id}/comments/{comments-id}", postId, commentId)
                                .with(user(memberDetails)))
                .andExpect(status().isOk());
    }
}