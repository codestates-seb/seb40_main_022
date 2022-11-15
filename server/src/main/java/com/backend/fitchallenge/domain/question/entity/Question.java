package com.backend.fitchallenge.domain.question.entity;

import com.backend.fitchallenge.domain.answer.entity.Answer;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.question.dto.request.QuestionCreate;
import com.backend.fitchallenge.domain.question.dto.request.QuestionUpdate;
import com.backend.fitchallenge.global.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Question extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUESTION_ID", nullable = false)
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "CONTENT", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(value = EnumType.STRING)
    private QuestionTag questionTag;

    @Column(name = "VIEW", nullable = false)
    private Long view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    @Builder
    private Question(String title, String content, QuestionTag questionTag, Long view, Member member) {
        this.title = title;
        this.content = content;
        this.questionTag = questionTag;
        this.view = view;
        this.member = member;
    }

    public static Question createQuestion(QuestionCreate questionCreate, Member member) {
        return Question.builder()
                .title(questionCreate.getTitle())
                .content(questionCreate.getContent())
                .questionTag(QuestionTag.from(questionCreate.getTag()))
                .view(0L)
                .member(member)
                .build();
    }

    public void updateQuestion(QuestionUpdate questionUpdate) {
        String changedTitle = questionUpdate.getTitle();
        String changedContent = questionUpdate.getContent();
        String changedTag = questionUpdate.getTag();

        this.title = changedTitle == null ? title : changedTitle;
        this.content = changedContent == null ? content : changedContent;
        this.questionTag = changedTag == null ? questionTag : QuestionTag.from(changedTag);
    }

    public enum QuestionTag {
        WORKOUT("운동"),
        DIET("식단"),
        POSTURE("자세"),
        HEALTH("헬스"),
        HABIT("습관");

        private final String value;

        QuestionTag(String value) {
            this.value = value;
        }

        /**
         * String -> enum
         */
        @JsonCreator
        public static QuestionTag from(String value) {
            for (QuestionTag tag : QuestionTag.values()) {
                if (tag.getValue().equals(value))
                    return tag;
            }
            return null;
        }

        /**
         * enum -> String
         */
        @JsonValue
        public String getValue() {
            return value;
        }
    }
}
