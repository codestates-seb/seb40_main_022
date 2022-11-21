package com.backend.fitchallenge.domain.calendar.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
public class RecordCreate {
    /**
     * [String 타입의 날짜/시간 정보를 LocalDate/LocalTime 타입의 필드로 변환]
     * - JSON 요청에 포함된 하단 'pattern' 형태의 문자열을 LocalDate/LocalTime 객체로 변환합니다.
     * - 심슨님이 보여주신 1일 단위 api 요청에서 날짜가 start로 넘어오길래 변수명은 start로 해놨습니다.
     *      DTO 단에서만 활용되고 추후 논의하고 변경해보겠습니다.
     * - 초는 제외할 수 있는 방법을 찾으면 추후 반영하겠습니다.
     */
    //
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    LocalDate start;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    LocalTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    LocalTime endTime;

    private List<SportsRequest> sports;
}
