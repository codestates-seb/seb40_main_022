package com.backend.fitchallenge.controller;

import com.backend.fitchallenge.util.QnaAndRecordUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class RecordControllerTest extends QnaAndRecordUtils {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;
}
