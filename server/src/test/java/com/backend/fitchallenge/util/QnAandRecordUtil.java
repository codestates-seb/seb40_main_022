package com.backend.fitchallenge.util;

import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.member.entity.ProfileImage;
import com.backend.fitchallenge.domain.question.entity.Question;
import com.backend.fitchallenge.domain.sports.dto.SportsRequest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class QnAandRecordUtil {
//    private final MultipartFile multipartFile = new MockMultipartFile();

    protected static final String QUESTION_TITLE = "테스트용 제목입니다.";
    protected static final String QUESTION_CONTENT = "테스트용 내용입니다.";
    protected static final String QUESTION_TAG_NAME = "식단";
    protected static final Question.QuestionTag QUESTION_TAG = Question.QuestionTag.DIET;
    protected static final String NEW_QUESTION_TITLE = "수정한 테스트용 제목입니다.";
    protected static final String NEW_QUESTION_CONTENT = "수정한 테스트용 내용입니다.";
    protected static final String NEW_QUESTION_TAG_NAME = "운동";
    protected static final Question.QuestionTag NEW_QUESTION_TAG = Question.QuestionTag.WORKOUT;
    protected static final LocalDateTime CREATED_AT = LocalDateTime.now();
    protected static final LocalDateTime MODIFIED_AT = LocalDateTime.now();

    protected static final String ANSWER_CONTENT = "테스트용 답변 내용입니다.";
    protected static final String COMMENT_CONTENT = "테스트용 댓글 내용입니다.";

    protected static final String MOD_TITLE = "수정한 테스트용 제목입니다.";
    protected static final String MOD_CONTENT = "수정한 테스트용 내용입니다.";

    protected static final LocalDate START = LocalDate.of(2022,12,6);
    protected static final LocalTime START_TIME = LocalTime.of(7, 0, 0);
    protected static final LocalTime END_TIME = LocalTime.of(9, 0, 0);
    protected static final String START_IMAGE_PATH = "https://d2j84io2oze31w.cloudfront.net/023ef5d4-3994-4f96-9d99-fa14a96dfeb3.jpg";
    protected static final String END_IMAGE_PATH = "https://d2j84io2oze31w.cloudfront.net/023ef5d4-3994-4f96-9d99-fa14a96dfeb3.jpg";
    protected static final LocalTime NEW_START_TIME = LocalTime.of(12, 0, 0);
    protected static final LocalTime NEW_END_TIME = LocalTime.of(17, 0, 0);
    protected static final String NEW_START_IMAGE_PATH = "https://d2j84io2sdfs31w.cloudfront.net/023ef5d4-3994-4f96-9d99-fa14a96dfeb3.jpg";
    protected static final String NEW_END_IMAGE_PATH = "https://f232io2oze31w.cloudfront.net/023ef5d4-3994-4f96-9d99-fa14a96dfeb3.jpg";
    protected static final SportsRequest SPORT1 = new SportsRequest(1L, 3, 30, 80);
    protected static final SportsRequest SPORT2 = new SportsRequest(1L, 4, 20, 50);
    protected static final SportsRequest SPORT3 = new SportsRequest(1L, 5, 10, 50);

    protected static final String EMAIL = "test@email.com";
    protected static final String PASSWORD = "test1234";
    protected static final String USERNAME = "홍길동";
    protected static final String PROFILE_IMAGE_PATH = "https://d34323o2oze21w.cloudfront.net/023ef5d4-3994-4f96-9d99-sfdfl23dfeb3.jpg";
    protected static final List<String> ROLES = List.of("USER");
    protected static final List<MockMultipartFile> FILES;

    static {
        try {
            FILES = List.of(
                    new MockMultipartFile("image", "test.png", "image/png", new FileInputStream(""))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected static final String FILEPATH = "https://d2j84io2oze31w.cloudfront.net/023ef5d4-3994-4f96-9d99-fa14a96dfeb3.jpg";

    protected static final Member member = Member.createBuilder()
            .email(EMAIL)
            .password(PASSWORD)
            .username(USERNAME)
            .profileImage(ProfileImage.createWithPath(PROFILE_IMAGE_PATH))
            .build();

    protected static final Question question = Question.builder()
            .title(QUESTION_TITLE)
            .content(QUESTION_CONTENT)
            .questionTag(QUESTION_TAG)
            .view(0L)
            .member(member)
            .build();

}
