package com.backend.fitchallenge.domain.post.dto;

import com.backend.fitchallenge.domain.tag.dto.TagDto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCreateVO {

    @NotBlank
    private String content;

    private List<TagDto> tagDtos;


    private List<MultipartFile> files;

    @Builder
    public PostCreateVO(String content, List<TagDto> tagDtos, List<MultipartFile> files) {
        this.content = content;
        this.tagDtos = tagDtos;
        this.files = files;
    }
}
