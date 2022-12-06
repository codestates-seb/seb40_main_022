package com.backend.fitchallenge.controller;


import com.backend.fitchallenge.domain.answercomment.dto.request.AnswerCommentCreate;
import com.backend.fitchallenge.domain.answercomment.dto.request.AnswerCommentUpdate;
import com.backend.fitchallenge.domain.answercomment.service.AnswerCommentService;
import com.backend.fitchallenge.util.QnAandRecordUtil;
import com.google.gson.Gson;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AnswerCommentControllerTest extends QnAandRecordUtil {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private AnswerCommentService answerCommentService;

    @Test
    @DisplayName("댓글 등록 테스트")
    void create() throws Exception {
        //given
        AnswerCommentCreate answerCommentCreate = new AnswerCommentCreate(COMMENT_CONTENT);
        given(answerCommentService.createAnswerComment(anyLong(), anyLong(), any(AnswerCommentCreate.class)))
                .willReturn(1L);
        String content = gson.toJson(answerCommentCreate);

        //when
        ResultActions actions =
                mockMvc.perform(
                        post("/questions/*/answers/{answer-id}/comments")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L));

    }

    @Test
    @DisplayName("댓글 수정 테스트")
    void update() throws Exception {
        //given
        Long answerCommentId = 1L;
        AnswerCommentUpdate answerCommentUpdate = new AnswerCommentUpdate(MOD_CONTENT);
        given(answerCommentService.updateAnswerComment(anyLong(), anyLong(), any(AnswerCommentUpdate.class)))
                .willReturn(answerCommentId);
        String content = gson.toJson(answerCommentUpdate);

        //when
        ResultActions actions =
                mockMvc.perform(
                        patch("/questions/*/answers/{answer-id}/comments")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L));

    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    void delete() throws Exception {
        //given
        Long answerCommentId = 1L;
        given(answerCommentService.deleteAnswerComment(anyLong(), anyLong()))
                .willReturn(answerCommentId);

        //when
        ResultActions actions = mockMvc.perform(
                MockMvcRequestBuilders.delete("questions/*/answers/{answer-id}")
        );

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L));

    }
}
