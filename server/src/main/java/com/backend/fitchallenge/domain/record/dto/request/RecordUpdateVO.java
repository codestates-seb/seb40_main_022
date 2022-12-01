package com.backend.fitchallenge.domain.record.dto.request;

import com.backend.fitchallenge.domain.sports.dto.SportsRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;
import java.util.List;

@Getter
public class RecordUpdateVO {

    /**
     * [String 타입의 시간 정보를 LocalTime 타입의 필드로 변환]
     * - JSON 요청에 포함된 하단 'pattern' 형태의 문자열을 LocalTime 객체로 변환합니다.
     * - 초는 제외할 수 있는 방법을 찾으면 추후 반영하겠습니다.
     */
    @DateTimeFormat(pattern = "HH:mm:ss")
    LocalTime startTime;

    @DateTimeFormat(pattern = "HH:mm:ss")
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
        this.sports = sports != null ? sports : List.of();
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
