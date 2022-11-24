package com.backend.fitchallenge.domain.calendar.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;

@Getter
public class RecordUpdate {

    /**
     * [String 타입의 시간 정보를 LocalTime 타입의 필드로 변환]
     * - JSON 요청에 포함된 하단 'pattern' 형태의 문자열을 LocalTime 객체로 변환합니다.
     * - 초는 제외할 수 있는 방법을 찾으면 추후 반영하겠습니다.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    LocalTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    LocalTime endTime;

    private String startImagePath;

    private String endImagePath;

    private List<SportsRequest> sports;
}
