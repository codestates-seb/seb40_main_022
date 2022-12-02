package com.backend.fitchallenge.domain.question.dto.response;

import com.backend.fitchallenge.domain.member.dto.response.extract.MemberResponse;
import com.backend.fitchallenge.domain.question.entity.Question;
import com.backend.fitchallenge.domain.question.entity.QuestionDocument;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class SimpleQuestionResponse {
    private Long questionId;

    private String title;

    private String summary;

    private String tag;

    private String picture;

    private Long view;

    private Integer answerCount;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private MemberResponse member;

    @Builder
    public SimpleQuestionResponse(Long questionId,
                                  String title,
                                  String content,
                                  String tag,
                                  String picture,
                                  Long view,
                                  Integer answerCount,
                                  LocalDateTime createdAt,
                                  LocalDateTime modifiedAt,
                                  MemberResponse member){
        this.questionId = questionId;
        this.title = title;
        this.summary = getSummary(content);
        this.tag = tag;
        this.picture = picture;
        this.view = view;
        this.answerCount = answerCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.member = member;
    }

    public static SimpleQuestionResponse of(Question question,
                                            Integer answerCount,
                                            String picture,
                                            MemberResponse memberResponse) {
        return SimpleQuestionResponse.builder()
                .questionId(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .tag(question.getQuestionTag().getValue())
                .picture(picture)
                .view(question.getView())
                .answerCount(answerCount)
                .createdAt(question.getCreatedAt())
                .modifiedAt(question.getModifiedAt())
                .member(memberResponse)
                .build();
    }

    public static SimpleQuestionResponse of(QuestionDocument questionDocument,
                                            MemberResponse memberResponse) {
        return SimpleQuestionResponse.builder()
                .questionId(questionDocument.getId())
                .title(questionDocument.getTitle())
                .content(questionDocument.getContent())
                .tag(questionDocument.getTag())
                .view(questionDocument.getView())
                .answerCount(questionDocument.getAnswerCount())
                .createdAt(questionDocument.getCreatedAt())
                .modifiedAt(questionDocument.getModifiedAt())
                .member(memberResponse)
                .build();
    }

    private String getSummary(String content) {
        return content.length() > 100 ? content.substring(0, 100) + "..." : content;
    }
}
