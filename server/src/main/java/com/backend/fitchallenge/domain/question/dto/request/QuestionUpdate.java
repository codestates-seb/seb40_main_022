package com.backend.fitchallenge.domain.question.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionUpdate {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String tag;

    private List<MultipartFile> files;

    @Builder
    public QuestionUpdate(String title, String content, String tag) {
        this.title = title;
        this.content = content;
        this.tag = tag;
    }
}
