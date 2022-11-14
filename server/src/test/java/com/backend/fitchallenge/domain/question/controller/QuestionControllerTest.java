package com.backend.fitchallenge.domain.question.controller;

import com.backend.fitchallenge.domain.question.dto.request.QuestionCreate;
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
                .title("저에게 맞는 운동 추천해주실 수 있나요?")
                .content("관련 상세 정보는 하단에 적어두었습니다.")
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
        MvcResult result = actions
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void update() {
    }

    @Test
    void details() {
    }

    @Test
    void list() {
    }

    @Test
    void delete() {
    }
}