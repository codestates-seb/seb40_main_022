package com.backend.fitchallenge.domain.record.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
public class TimePictureUpdateVO {

    private String point;

    private String filePath;

    private MultipartFile file;

    public TimePictureUpdateVO(String point, String filePath, MultipartFile file) {
        this.point = point;
        this.filePath = filePath;
        this.file = file;
    }
}
