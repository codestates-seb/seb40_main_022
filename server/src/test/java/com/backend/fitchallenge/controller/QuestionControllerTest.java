package com.backend.fitchallenge.controller;

import com.backend.fitchallenge.domain.question.dto.request.QuestionCreateVO;
import com.backend.fitchallenge.domain.question.dto.request.QuestionUpdateVO;
import com.backend.fitchallenge.domain.question.dto.response.DetailQuestionResponse;
import com.backend.fitchallenge.util.QnaAndRecordUtils;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTest extends QnaAndRecordUtils {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Test
    @DisplayName("질문 등록")
    void createTest() throws Exception {
        //given
        QuestionCreateVO questionCreateVO = QuestionCreateVO.builder()
                .title(questionTitle)
                .content(questionContent)
                .build();
        String content = gson.toJson(questionCreateVO);

        given(questionService.createQuestion(anyLong(), any(QuestionCreateVO.class), anyList()))
                .willReturn(1L);

        //when
        ResultActions actions =
                mockMvc.perform(
                        post("/questions")
                                .with(user(memberDetails))
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L));

    }

    @Test
    @DisplayName("질문 상세 조회")
    void detailsTest() throws Exception {
        //given
        DetailQuestionResponse detailQuestionResponse = DetailQuestionResponse.builder()
                .questionId(1L)
                .title(questionTitle)
                .content(questionContent)
                .tag(questionTag)
                .pictures(picturePaths)
                .view(1L)
                .answerCount(1)
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .memberResponse(memberResponse)
                .answers(List.of(answerResponse))
                .build();
        given(questionService.getQuestion(anyLong())).willReturn(detailQuestionResponse);

        //when
        ResultActions actions = mockMvc.perform(
                get("/questions/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.questionId").value(detailQuestionResponse.getQuestionId()))
                .andExpect(jsonPath("$.title").value(detailQuestionResponse.getTitle()))
                .andExpect(jsonPath("$.content").value(detailQuestionResponse.getContent()))
                .andExpect(jsonPath("$.pictures").value(detailQuestionResponse.getPictures()))
                .andExpect(jsonPath("$.view").value(detailQuestionResponse.getView()))
                .andExpect(jsonPath("$.answerCount").value(detailQuestionResponse.getAnswerCount()))
                .andExpect(jsonPath("$.createdAt").value(detailQuestionResponse.getCreatedAt()))
                .andExpect(jsonPath("$.modifiedAt").value(detailQuestionResponse.getModifiedAt()))
                .andExpect(jsonPath("$.questionWriter").value(detailQuestionResponse.getQuestionWriter()))
                .andExpect(jsonPath("$.answers").value(detailQuestionResponse.getAnswers()));
    }

    @Test
    @DisplayName("질문 목록 조회")
    void listTest() {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("질문 검색 목록 조회")
    void searchListTest() {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("질문 수정")
    void updateTest() throws Exception {
        //given
        QuestionUpdateVO questionUpdateVO = QuestionUpdateVO.builder()
                .title(questionTitle)
                .content(questionContent)
                .build();
        String content = gson.toJson(questionUpdateVO);

        given(questionService.updateQuestion(anyLong(), anyLong(), any(QuestionUpdateVO.class)))
                .willReturn(1L);

        //when
        ResultActions actions = mockMvc.perform(
                post("questions/{id}")
                        .with(user(memberDetails))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L));

    }

    @Test
    @DisplayName("질문 삭제")
    void deleteTest() throws Exception {
        //given
        given(questionService.deleteQuestion(anyLong(), anyLong()))
                .willReturn(1L);

        //when
        ResultActions actions = mockMvc.perform(
                delete("/questions/1")
                        .with(user(memberDetails)));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L));
    }
}
