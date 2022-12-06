package com.backend.fitchallenge.controller;

import com.backend.fitchallenge.domain.member.dto.response.extract.MemberResponse;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.entity.ProfileImage;
import com.backend.fitchallenge.domain.question.dto.request.QuestionCreateVO;
import com.backend.fitchallenge.domain.question.dto.request.QuestionUpdateVO;
import com.backend.fitchallenge.domain.question.dto.response.DetailQuestionResponse;
import com.backend.fitchallenge.domain.question.entity.Question;
import com.backend.fitchallenge.domain.question.service.QuestionService;
import com.backend.fitchallenge.domain.answer.dto.response.AnswerResponse;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import com.backend.fitchallenge.util.QnAandRecordUtil;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTest extends QnAandRecordUtil {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private QuestionService questionService;

    private MemberDetails memberDetails;

    private List<MultipartFile> files;



    @Test
    @DisplayName("질문 등록 테스트")
    void create() throws Exception {
        //given
        QuestionCreateVO questionCreateVO = QuestionCreateVO.builder()
                .title(QUESTION_TITLE)
                .content(QUESTION_CONTENT)
                .tag(QUESTION_TAG_NAME)
                .build();

        given(questionService.createQuestion(anyLong(), any(QuestionCreateVO.class), List.of(anyString())))
                .willReturn(1L);

        String content = gson.toJson(questionCreateVO);

        //when
        ResultActions actions = mockMvc.perform(
                post("/questions")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L));
    }

    @Test
    @DisplayName("특정 질문 조회 테스트")
    void details() throws Exception {
        //given
        MemberResponse memberResponse = new MemberResponse(1L, USERNAME, PROFILE_IMAGE_PATH);

        Question question = Question.builder()
                .title(QUESTION_TITLE)
                .content(QUESTION_CONTENT)
                .questionTag(QUESTION_TAG)
                .view(0L)
                .member(member)
                .build();

        AnswerResponse answerResponse = AnswerResponse.builder()
                .answerId(1L)
                .isAccepted(false)
                .createdAt(CREATED_AT)
                .modifiedAt(MODIFIED_AT)
                .answerWriter(memberResponse)
                .build();

        DetailQuestionResponse detailQuestionResponse = DetailQuestionResponse.of(question, memberResponse, List.of(answerResponse));

        given(questionService.getQuestion(anyLong()))
                .willReturn(detailQuestionResponse);

        //when
        ResultActions actions = mockMvc.perform(
                get("/questions/{id}")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.questionId").value(1L))
                .andExpect(jsonPath("$.title").value(QUESTION_TITLE))
                .andExpect(jsonPath("$.content").value(QUESTION_CONTENT))
                .andExpect(jsonPath("$.tag").value(QUESTION_TAG_NAME))
                .andExpect(jsonPath("$.answerCount").value(1))
                .andExpect(jsonPath("$.answerWriter.id").value(1L))
                .andExpect(jsonPath("$.answerWriter.username").value(USERNAME))
                .andExpect(jsonPath("$.answerWriter.profileImage").value(PROFILE_IMAGE_PATH))
                .andExpect(jsonPath("$.answers").exists())
                .andExpect(jsonPath("$.answers", hasSize(1)));

    }

    @Test
    @DisplayName("질문 목록 조회 테스트")
    void list() throws Exception {

        ResultActions actions = mockMvc.perform(
                get("/questions")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("page", "1")
                        .param("sort", "recent")
        );
        actions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("질문 수정 테스트")
    void update() throws Exception {
        QuestionUpdateVO questionUpdateVO = QuestionUpdateVO.builder()
                .title(NEW_QUESTION_TITLE)
                .content(NEW_QUESTION_CONTENT)
                .tag(NEW_QUESTION_TAG_NAME)
                .build();

        given(questionService.updateQuestion(anyLong(), anyLong(), any(QuestionUpdateVO.class)))
                .willReturn(1L);

        String content = gson.toJson(questionUpdateVO);

        //when
        ResultActions actions = mockMvc.perform(
                post("/questions")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L));
    }

    @Test
    @DisplayName("질문 삭제 테스트")
    void delete() throws Exception {
        //given
        Long questionId = 1L;
        given(questionService.deleteQuestion(anyLong(), anyLong()))
                .willReturn(questionId);

        //when
        ResultActions actions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/question/{id}"));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L));
    }
}
