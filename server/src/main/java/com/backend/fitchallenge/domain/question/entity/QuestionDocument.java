package com.backend.fitchallenge.domain.question.entity;

import com.backend.fitchallenge.domain.answer.entity.Answer;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.global.audit.Auditable;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.annotations.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(indexName = "question")
@Mapping(mappingPath = "elastic/question-mapping.json")
@Setting(settingPath = "elastic/question-setting.json", shards = 2)
@Slf4j
public class QuestionDocument {

    @Id
    private Long id;

    private String title;

    private String content;

    private String tag;

    private String picture;

    private Long view;

    private Long memberId;

    private String username;

    private String profileImage;

    private Integer answerCount;

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime modifiedAt;

    @Builder
    public QuestionDocument(Long id,
                            String title,
                            String content,
                            String tag,
                            String picture,
                            Long view,
                            Long memberId,
                            String username,
                            String profileImage,
                            Integer answerCount,
                            LocalDateTime createdAt,
                            LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.picture = picture;
        this.view = view;
        this.memberId = memberId;
        this.username = username;
        this.profileImage = profileImage;
        this.answerCount = answerCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static QuestionDocument from(Question question) {
        Member writer = question.getMember();

        return QuestionDocument.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .tag(question.getQuestionTag().getValue())
                .view(question.getView())
                .memberId(writer.getId())
                .username(writer.getUsername())
                .profileImage((writer.getProfileImage().getPath()))
                .answerCount(question.getAnswers().size())
                .createdAt(question.getCreatedAt())
                .modifiedAt(question.getModifiedAt())
                .build();
    }

}
