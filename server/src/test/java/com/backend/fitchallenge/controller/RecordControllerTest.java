package com.backend.fitchallenge.controller;


import com.backend.fitchallenge.domain.post.service.AwsS3Service;
import com.backend.fitchallenge.domain.record.dto.request.RecordCreateVO;
import com.backend.fitchallenge.domain.record.dto.request.RecordUpdateVO;
import com.backend.fitchallenge.domain.record.dto.response.DetailRecordResponse;
import com.backend.fitchallenge.domain.record.service.RecordService;
import com.backend.fitchallenge.util.QnAandRecordUtil;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RecordControllerTest extends QnAandRecordUtil {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private RecordService recordService;

    @MockBean
    private AwsS3Service awsS3Service;

    @Test
    @DisplayName("운동기록 등록 테스트")
    void create() throws Exception {
        //given
        RecordCreateVO recordCreateVO = RecordCreateVO.builder()
                .start(START)
                .startTime(START_TIME)
                .endTime(END_TIME)
                .startImagePath(START_IMAGE_PATH)
                .endImagePath(END_IMAGE_PATH)
                .sports(List.of(SPORT1, SPORT2))
                .build();
        given(recordService.createRecord(anyLong(), any(RecordCreateVO.class)))
                .willReturn(1L);

        String content = gson.toJson(recordCreateVO);

        //when
        ResultActions actions =
                mockMvc.perform(
                        post("/records")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L));
    }

    @Test
    @DisplayName("특정 운동기록 조회 테스트")
    void details() throws Exception {
        Long recordId = 1L;

        ResultActions actions = mockMvc.perform(
                get("records/{record-id}", recordId)
                        .accept(MediaType.APPLICATION_JSON)
        );

        actions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("월별 운동기록 조회 테스트")
    void list() throws Exception {
        ResultActions actions = mockMvc.perform(
                get("/records")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("month", "11")
        );

        actions.andExpect(status().isOk());

    }

    @Test
    @DisplayName("운동기록 수정 테스트")
    void update() throws Exception {
        //given
        RecordUpdateVO recordUpdateVO = RecordUpdateVO.builder()
                .startTime(NEW_START_TIME)
                .endTime(NEW_END_TIME)
                .startImagePath(NEW_START_IMAGE_PATH)
                .endImagePath(NEW_END_IMAGE_PATH)
                .sports(List.of(SPORT3))
                .build();
        given(recordService.updateRecord(anyLong(), anyLong(), any(RecordUpdateVO.class)))
                .willReturn(1L);

        String content = gson.toJson(recordUpdateVO);

        //when
        ResultActions actions =
                mockMvc.perform(
                        post("/records/{record-id}")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L));

    }

    @Test
    @DisplayName("운동기록 삭제 테스트")
    void delete() throws Exception {
        //given
        Long recordId = 1L;
        given(recordService.deleteRecord(anyLong(), anyLong()))
                .willReturn(recordId);

        //when
        ResultActions actions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/records/{record-id}"));

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1L));

    }

    @Test
    @DisplayName("운동 인증사진 등록 테스트")
    void createPicture() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "image", "image.png",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                "image".getBytes());
        given(awsS3Service.StoreFile(List.of(any(MultipartFile.class))))
                .willReturn(List.of("https://d2j84io2oze31w.cloudfront.net/023ef5d4-3994-4f96-9d99-fa14a96dfeb3.jpg"));

        mockMvc.perform(
                multipart("/records/pictures")
                        .file(file)
        ).andExpect(status().isOk());

    }

    @Test
    @DisplayName("운동 인증사진 수정 테스트")
    void updatePicture() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "image", "image.png",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                "image".getBytes());

        given(awsS3Service.UpdateFile(
                List.of(anyString()),
                List.of(any(MultipartFile.class)
                )))
                .willReturn(List.of("https://d2j84io2oze31w.cloudfront.net/023ef5d4-3994-4f96-9d99-fa14a96dfeb3.jpg"));

        mockMvc.perform(
                multipart("/records/pictures/update")
                        .file(file)
        ).andExpect(status().isOk());
    }
}
