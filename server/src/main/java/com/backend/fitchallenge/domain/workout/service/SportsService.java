package com.backend.fitchallenge.domain.workout.service;

import com.backend.fitchallenge.domain.workout.dto.request.SportsDto;
import com.backend.fitchallenge.domain.workout.dto.response.SimpleSportsResponse;
import com.backend.fitchallenge.domain.workout.entity.Sports;
import com.backend.fitchallenge.domain.workout.repository.SportsRepository;
import com.backend.fitchallenge.global.dto.response.MultiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SportsService {

    private final SportsRepository sportsRepository;

    public Sports addSports(SportsDto sportsDto) {

        Sports sports = sportsDto.toEntity();
        if (!sportsExist(sports.getName())) {
            sportsRepository.save(sports);
        }

        return sports;
    }

    private boolean sportsExist(String name) {
        return sportsRepository.existsByName(name);
    }

    public MultiResponse<?> getSportsList(Pageable pageable) {

        Page<SimpleSportsResponse> sportsResponses = new PageImpl<>(sportsRepository.findAll(pageable).stream()
                .map(SimpleSportsResponse::toResponse).collect(Collectors.toList()));

        return MultiResponse.of(sportsResponses);
    }

    /**
     * [부위별 운동 조회]
     * 1. 운동 부위 이름을 BodyPart(enum) 객체로 변환합니다.
     * 2. 부위별 운동을 조회하고 결과값을 반환합니다.
     */
    public MultiResponse<?> getSportsListByPart(String bodyPart, Pageable pageable) {

        Page<SimpleSportsResponse> sportsResponses = new PageImpl<>(
                sportsRepository.findByBodyPart(Sports.BodyPart.from(bodyPart))
                        .stream()
                        .map(SimpleSportsResponse::toResponse)
                        .collect(Collectors.toList()));

        return MultiResponse.of(sportsResponses);
    }
}
