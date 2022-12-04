package com.backend.fitchallenge.domain.record.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
//현재는 운동의 시작 사진과 종료 사진을 list에 순서대로 담게 되어 있습니다.
//운동시간을 추출할 사진을 등록할 때 구분값을 주었기에 프론트엔드 단에서 순서대로 담기만 하면 문제가 되지 않지만
//추후 변경 사항이 생길까봐
public class ImagePathRequest {
    @NotBlank(message = "운동 시작 사진의 경로를 입력해주세요.")
    String startImagePath;

    @NotBlank(message = "운동 종료 사진의 경로를 입력해주세요.")
    String endImagePath;
}
