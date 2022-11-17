package com.backend.fitchallenge.domain.workout.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CalendarController {
    /**
     * [캘린더 등록 방법 논의 필요]
     * 캘린더 등록(생성)과 관련해 생각해놓은 방법은 다음과 같습니다.
     * 1. 스케쥴러에 의해 일정 기간이 되면 자동으로 등록
     * 2. 관리자가 직접 캘린더 등록(혹은 삭제)
     * 3. 사용자가 기록을 등록하면 자동으로 캘린더 등록
     *
     * 본 컨트롤러는 2번의 경우에 필요합니다.
     *
     * 개인적으로는 1번이 최선의 선택지라고 생각합니다.
     * 1번에 비해 2번은 채택 근거가 불명확하고 3번은 방법이 불명확합니다.
     *
     * 생각하지 못한 필요가 생길 수도 있기에 컨트롤러 틀은 만들어놨는데
     * 추가 작업은 논의를 거친 후 필요 여부에 따라 진행할 생각입니다.
     */
}
