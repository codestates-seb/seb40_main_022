package com.backend.fitchallenge.domain.question.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class QuestionCreateVO {

    private String title;

    private String content;

    private String tag;

    private List<MultipartFile> files;

    @Builder
    public QuestionCreateVO(String title, String content, String tag, List<MultipartFile> files) {
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.files = files != null ? files : List.of();
    }
}
