package com.backend.fitchallenge.controller;

import com.backend.fitchallenge.domain.answer.dto.request.AnswerCreate;
import com.backend.fitchallenge.domain.answer.dto.request.AnswerUpdate;
import com.backend.fitchallenge.domain.answer.service.AnswerService;
import com.backend.fitchallenge.domain.question.service.QuestionService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AnswerControllerTest extends QnAandRecordUtil {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private AnswerService answerService;

    @Test
    @DisplayName("답변 등록 테스트")
    void create() throws Exception {
        //given
        Long answerId = 1L;
        AnswerCreate answerCreate = new AnswerCreate(ANSWER_CONTENT);
        given(answerService.createAnswer(anyLong(), anyLong(), any(AnswerCreate.class)))
                .willReturn(answerId);
        String content = gson.toJson(answerCreate);

        //when
        ResultActions actions =
                mockMvc.perform(
                        post("/answers")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(answerId));
    }

    @Test
    @DisplayName("답변 수정 테스트")
    void update() throws Exception {
        //given
        Long answerId = 1L;
        AnswerUpdate answerUpdate = new AnswerUpdate(ANSWER_CONTENT, true);
        given(answerService.updateAnswer(anyLong(), anyLong(), any(AnswerUpdate.class)))
                .willReturn(answerId);
        String content = gson.toJson(answerUpdate);

        //when
        ResultActions actions = mockMvc.perform(
                patch("/questions/{id}/answers/{answer-id}")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L));

    }

    @Test
    @DisplayName("답변 삭제 테스트")
    void delete() throws Exception {
        //given
        Long answerId = 1L;
        given(answerService.deleteAnswer(anyLong(), anyLong()))
                .willReturn(answerId);

        //when
        ResultActions actions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/questions/{id}/answers/{answer-id}")
        );

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L));

    }

    @Test
    @DisplayName("답변 채택 테스트")
    void accept() throws Exception {
        //given
        Long answerId = 1L;
        given(answerService.accept(anyLong(), anyLong(), anyLong()))
                .willReturn(answerId);

        //when
        ResultActions actions =
                mockMvc.perform(
                        post("/questions/{id}/answers/{answer-id}")
                );

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(answerId));
    }
}
