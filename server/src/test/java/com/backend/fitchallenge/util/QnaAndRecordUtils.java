package com.backend.fitchallenge.util;

import com.backend.fitchallenge.domain.answer.dto.response.AnswerResponse;
import com.backend.fitchallenge.domain.answer.service.AnswerService;
import com.backend.fitchallenge.domain.answercomment.dto.response.AnswerCommentResponse;
import com.backend.fitchallenge.domain.answercomment.service.AnswerCommentService;
import com.backend.fitchallenge.domain.member.dto.response.extract.MemberResponse;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.question.service.QuestionService;
import com.backend.fitchallenge.domain.record.service.RecordService;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;

public class QnaAndRecordUtils {

    @MockBean
    protected QuestionService questionService;

    @MockBean
    protected AnswerService answerService;

    @MockBean
    protected AnswerCommentService answerCommentService;

    @MockBean
    protected RecordService recordService;

    protected final String questionTitle = "테스트용 질문 제목입니다.";

    protected final String questionContent = "테스트용 질문 내용입니다.";

    protected final String questionTag = "운동";

    protected final List<String> picturePaths = List.of(
            "https://d2j84io2oze31w.cloudfront.net/023ef5d4-3994-4f96-9d99-fa14a96dfeb3.jpg",
            "https://d2j84io2oze31w.cloudfront.net/338bc731-33bf-4c2f-b3a8-a111b0017a61.jpg");

    protected final LocalDateTime createdAt = LocalDateTime.now();
    protected final LocalDateTime modifiedAt = LocalDateTime.now();

    protected final MemberResponse memberResponse = new MemberResponse(1L, "홍길동", "https://d2j84io2oze31w.cloudfront.net/023ef5d4-3994-4f96-9d99-fa14a96dfeb3.jpg");

    protected final AnswerCommentResponse answerCommentResponse =
            AnswerCommentResponse.builder()
                    .answerCommentId(1L)
                    .content("테스트용 댓글 내용입니다.")
                    .createdAt(createdAt)
                    .modifiedAt(modifiedAt)
                    .commentWriter(memberResponse)
                    .build();

    protected final AnswerResponse answerResponse = AnswerResponse.builder()
            .answerId(1L)
            .content("테스트용 답변 내용입니다.")
            .isAccepted(false)
            .createdAt(createdAt)
            .modifiedAt(modifiedAt)
            .answerWriter(memberResponse)
            .comments(List.of(answerCommentResponse))
            .build();

    protected final MemberDetails memberDetails = MemberDetails.builder()
            .memberId(1L)
            .email("email@example.com")
            .password("1234")
            .build();


}
