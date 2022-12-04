package com.backend.fitchallenge.domain.question.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class QuestionPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "path", nullable = false)
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Builder
    public QuestionPicture(String path, Question question) {
        this.path = path;
        this.question = question;
    }

    public static void create(String path, Question question) {
        QuestionPicture picture = QuestionPicture.builder()
                .path(path)
                .question(question)
                .build();

        question.getQuestionPictures().add(picture);
    }

    public static QuestionPicture createWithEmptyPath() {
        return QuestionPicture.builder().path("").build();
    }
}
