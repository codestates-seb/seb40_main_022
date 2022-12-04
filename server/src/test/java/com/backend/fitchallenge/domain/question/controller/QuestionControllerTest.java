/*
package com.backend.fitchallenge.domain.question.controller;

import com.backend.fitchallenge.domain.question.dto.request.QuestionCreate;
import com.backend.fitchallenge.domain.question.dto.request.QuestionUpdate;
import com.backend.fitchallenge.domain.question.service.QuestionService;
import com.google.gson.Gson;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    QuestionService questionService;

    @Test
    void create() throws Exception {
        //given
        QuestionCreate questionCreate = QuestionCreate.builder()
                .title("저에게 맞는 운동 루틴 추천해주실 수 있나요?")
                .content("주 3회 2시간씩 운동 가능합니다.")
                .build();

        String content = gson.toJson(questionCreate);

        given(questionService.createQuestion(Mockito.anyLong(), Mockito.any(QuestionCreate.class)))
                .willReturn(1L);

        //when
        ResultActions actions = mockMvc.perform(
                post("/questions")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content));

        //then
        actions.andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        //given
        QuestionUpdate questionUpdate = QuestionUpdate.builder()
                .title("저에게 맞는 운동 루틴 추천해주세요")
                .content("주 4회 1시간씩 운동 가능합니다.")
                .build();

        String content = gson.toJson(questionUpdate);

        given(questionService.updateQuestion(Mockito.anyLong(), Mockito.anyLong(), Mockito.any(QuestionUpdate.class)))
                .willReturn(1L);

        //when
        ResultActions actions = mockMvc.perform(
                patch("/questions/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        //then
        actions.andExpect(status().isOk());
    }

    @Test
    void details() {
        //given

        //when

        //then

    }

    @Test
    void list() {
        //given

        //when

        //then

    }

    @Test
    void delete() {
        //given

        //when

        //then

    }
}*/
