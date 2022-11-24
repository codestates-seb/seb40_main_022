package com.backend.fitchallenge.domain.calendar.dto.response;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class TimePictureResponse {

    private String imagePath;

    private String point;

    private LocalTime time;

    private TimePictureResponse(String imagePath, String point, LocalTime time) {
        this.imagePath = imagePath;
        this.point = point;
        this.time = time;
    }

    public static TimePictureResponse of(String imagePath, String point, LocalTime time) {
        return new TimePictureResponse(imagePath, point, time);
    }
}
