package com.backend.fitchallenge.domain.calendar.dto.response;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class TimePictureResponse {

    private String point;

    private String imagePath;

    private TimePictureResponse(String point, String imagePath) {
        this.point = point;
        this.imagePath = imagePath;
    }

    public static TimePictureResponse of(String point, String imagePath) {
        return new TimePictureResponse(point, imagePath);
    }
}
