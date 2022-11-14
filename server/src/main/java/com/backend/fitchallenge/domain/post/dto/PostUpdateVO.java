package com.backend.fitchallenge.domain.post.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostUpdateVO {

    @NotBlank
    private String content;

    private List<TagDto> tagDtos;

    private List<MultipartFile> files;

    @Builder
    public PostUpdateVO(String content, List<TagDto> tagDtos, List<MultipartFile> files) {
        this.content = content;
        this.tagDtos = tagDtos;
        this.files = files;
    }
}
