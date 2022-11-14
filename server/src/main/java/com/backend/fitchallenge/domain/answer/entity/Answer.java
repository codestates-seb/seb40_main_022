package com.backend.fitchallenge.domain.answer.entity;

import com.backend.fitchallenge.domain.answer.dto.request.AnswerCreate;
import com.backend.fitchallenge.domain.answer.dto.request.AnswerUpdate;
import com.backend.fitchallenge.domain.answercomment.entity.AnswerComment;
import com.backend.fitchallenge.domain.member.Member;
import com.backend.fitchallenge.domain.question.entity.Question;
import com.backend.fitchallenge.global.audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@Getter
public class Answer extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANSWER_ID", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(name = "IS_ACCEPTED")
    private boolean isAccepted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerComment> comments = new ArrayList<>();

    @Builder
    private Answer(String content, boolean isAccepted, Question question, Member member) {
        this.content = content;
        this.isAccepted = isAccepted;
        this.question = question;
        this.member = member;
    }

    public static Answer createAnswer(AnswerCreate answerCreate, Question question, Member member) {
        return Answer.builder()
                .content(answerCreate.getContent())
                .isAccepted(false)
                .question(question)
                .member(member)
                .build();
    }

    public void updateAnswer(AnswerUpdate answerUpdate) {
        String changedContent = answerUpdate.getContent();

        if (changedContent != null) {
            this.content = changedContent;
        }
    }

    public void accept() {
        this.isAccepted = true;
    }

    public void unAccept() {
        this.isAccepted = false;
    }
}
