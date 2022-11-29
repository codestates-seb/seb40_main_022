package com.backend.fitchallenge.domain.record.dto.request;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
public class TimePictureVO {

    @NotBlank(message = "start 혹은 end를 입력해주세요.")
    private String point;

    @Valid
    @NotBlank(message = "파일을 등록해주세요")
    private MultipartFile file;

    public TimePictureVO(String point, MultipartFile file) {
        this.point = point;
        this.file = file;
    }
}
