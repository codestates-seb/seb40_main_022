package com.backend.fitchallenge.domain.calendar.dto.request;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
public class TimePictureUpdateVO extends TimePictureVO {

    private String point;

    @NotBlank(message = "기존 파일 경로를 입력해주세요.")
    private String filePath;

    @NotBlank(message = "새로운 파일을 등록해주세요.")
    private MultipartFile file;

    public TimePictureUpdateVO(String date, String point, MultipartFile file) {
        super(point, file);
    }
}
