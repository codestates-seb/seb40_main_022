package com.backend.fitchallenge.controller;

import com.backend.fitchallenge.domain.answercomment.dto.request.AnswerCommentCreate;
import com.backend.fitchallenge.domain.answercomment.dto.request.AnswerCommentUpdate;
import com.backend.fitchallenge.domain.answercomment.service.AnswerCommentService;
import com.backend.fitchallenge.util.QnaAndRecordUtils;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AnswerCommentControllerTest extends QnaAndRecordUtils {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Test
    @DisplayName("댓글 등록")
    void createTest() throws Exception {
        //given
        AnswerCommentCreate answerCommentCreate = new AnswerCommentCreate("테스트용 댓글입니다.");
        given(answerCommentService.createAnswerComment(anyLong(), anyLong(), any(AnswerCommentCreate.class)))
                .willReturn(1L);

        String content = gson.toJson(answerCommentCreate);

        //when & then
        ResultActions actions =
                mockMvc.perform(
                        post("questions/*/answers/1/comments")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content));

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L));
    }

    @Test
    @DisplayName("댓글 수정")
    void updateTest() throws Exception {
        //given
        AnswerCommentUpdate answerCommentUpdate = new AnswerCommentUpdate("테스트용 수정 댓글입니다.");
        String content = gson.toJson(answerCommentUpdate);

        given(answerCommentService.updateAnswerComment(anyLong(), anyLong(), any(AnswerCommentUpdate.class)))
                .willReturn(1L);

        //when
        ResultActions actions = mockMvc.perform(
                patch("/questions/*/answers/*/comments")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L));

    }

    @Test
    @DisplayName("댓글 삭제")
    void deleteTest() throws Exception {
        //given
        given(answerCommentService.deleteAnswerComment(anyLong(), 1L)).willReturn(1L);

        //when
        ResultActions actions = mockMvc.perform(
                delete("/questions/*/answers/*/comments/1")
        );

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L));
    }
}
