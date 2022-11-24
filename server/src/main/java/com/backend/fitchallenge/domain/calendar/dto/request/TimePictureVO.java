package com.backend.fitchallenge.domain.calendar.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
public class TimePictureVO {

    @NotBlank
    String date;

    @NotBlank(message = "start 혹은 end를 입력해주세요.")
    private String point;

    @Valid
    @NotBlank(message = "파일을 등록해주세요")
    private MultipartFile file;

    public TimePictureVO(String date, String point, MultipartFile file) {
        this.date = date;
        this.point = point;
        this.file = file;
    }
}
