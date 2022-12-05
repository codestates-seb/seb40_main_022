package com.backend.fitchallenge.controller;

import com.backend.fitchallenge.domain.answer.dto.request.AnswerCreate;
import com.backend.fitchallenge.domain.answer.dto.request.AnswerUpdate;
import com.backend.fitchallenge.domain.answer.service.AnswerService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AnswerControllerTest extends QnaAndRecordUtils {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Test
    @DisplayName("새로운 답변 등록")
    void createTest() throws Exception {
        //given
        AnswerCreate answerCreate = new AnswerCreate("테스트용 답변입니다.");

        given(answerService.createAnswer(anyLong(), anyLong(), Mockito.any(AnswerCreate.class)))
                .willReturn(1L);

        String content = gson.toJson(answerCreate);

        //when
        ResultActions actions =
                mockMvc.perform(
                        post("/questions/*/answers")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content));

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L));
    }

    @Test
    @DisplayName("답변 수정")
    void updateTest() throws Exception {
        //given
        AnswerUpdate answerUpdate = new AnswerUpdate("테스트용 수정 답변입니다.", false);
        String content = gson.toJson(answerUpdate);

        given(answerService.updateAnswer(anyLong(), anyLong(), Mockito.any(AnswerUpdate.class)))
                .willReturn(1L);

        //when
        ResultActions actions = mockMvc.perform(
                patch("/questions/*/answers/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L))
                .andReturn();

    }

    @Test
    @DisplayName("답변 삭제")
    void deleteTest() throws Exception {
        //given
        Long answerId = 1L;
        given(answerService.deleteAnswer(anyLong(), anyLong()))
                .willReturn(1L);

        //when
        ResultActions actions = mockMvc.perform(
                delete("/questions/*/answers/1"));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L));
    }

    @Test
    @DisplayName("답변 채택")
    void acceptTest() throws Exception {
        //given
        given(answerService.accept(anyLong(), anyLong(), anyLong())).willReturn(1L);

        //when
        ResultActions actions = mockMvc.perform(
                post("/questions/*/answers/1/accept"));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L));
    }
}
