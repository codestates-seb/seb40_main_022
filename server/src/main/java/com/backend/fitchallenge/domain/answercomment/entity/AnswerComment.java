package com.backend.fitchallenge.domain.answercomment.entity;

import com.backend.fitchallenge.domain.answer.dto.request.AnswerUpdate;
import com.backend.fitchallenge.domain.answer.entity.Answer;
import com.backend.fitchallenge.domain.answercomment.dto.request.AnswerCommentCreate;
import com.backend.fitchallenge.domain.answercomment.dto.request.AnswerCommentUpdate;
import com.backend.fitchallenge.domain.member.Member;
import com.backend.fitchallenge.global.audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AnswerComment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANSWER_COMMENT_ID", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Builder
    private AnswerComment(String content, Answer answer, Member member) {
        this.content = content;
        this.answer = answer;
        this.member = member;
    }

    public static AnswerComment createAnswerComment(AnswerCommentCreate answerCommentCreate, Answer answer, Member member) {
        return AnswerComment.builder()
                .content(answerCommentCreate.getContent())
                .answer(answer)
                .member(member)
                .build();
    }

    public void updateAnswerComment(AnswerCommentUpdate answerCommentUpdate) {
        String changedContent = answerCommentUpdate.getContent();

        if (changedContent != null) {
            this.content = changedContent;
        }
    }
}
