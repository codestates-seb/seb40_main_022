package com.backend.fitchallenge.domain.record.dto.request;

import com.backend.fitchallenge.domain.sports.dto.SportsRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecordUpdateVO {

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

    @Builder
    public RecordUpdateVO(LocalTime startTime, LocalTime endTime, String startImagePath, String endImagePath, List<SportsRequest> sports) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.startImagePath = startImagePath;
        this.endImagePath = endImagePath;
        this.sports = sports;
    }

    public boolean includesBothImages() {
        return this.startImagePath != null && this.endImagePath != null;
    }

    public boolean includesStartImage() {
        return this.startImagePath != null;
    }

    public boolean includesEndImage() {
        return this.endImagePath != null;
    }
}
