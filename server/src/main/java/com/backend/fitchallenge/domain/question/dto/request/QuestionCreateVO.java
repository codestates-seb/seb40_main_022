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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionCreateVO {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @NotBlank(message = "태그를 입력해주세요.")
    private String tag;

    private List<MultipartFile> files = new ArrayList<>();

    @Builder
    public QuestionCreateVO(String title, String content, String tag, List<MultipartFile> files) {
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.files = files;
    }
}
