package com.backend.fitchallenge.domain.calendar.service;

import com.backend.fitchallenge.domain.calendar.dto.request.SportsRequest;
import com.backend.fitchallenge.domain.calendar.dto.response.SimpleSportsResponse;
import com.backend.fitchallenge.domain.calendar.entity.Sports;
import com.backend.fitchallenge.domain.calendar.exception.SportsNotFound;
import com.backend.fitchallenge.domain.calendar.repository.SportsRepository;
import com.backend.fitchallenge.global.dto.request.PageRequest;
import com.backend.fitchallenge.global.dto.response.MultiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SportsService {

    private final SportsRepository sportsRepository;

    /**
     * [전체 운동 조회]
     * 운동을 부위별로 나누어 보여주는 게 디폴트라면
     * 실질적으로 유저가 사용할 일은 없어 보입니다.
     */
    public MultiResponse<?> getSportsList(Pageable pageable) {

        Page<SimpleSportsResponse> sportsResponses = new PageImpl<>(sportsRepository.findAll(pageable).stream()
                .map(SimpleSportsResponse::toResponse).collect(Collectors.toList()));

        return MultiResponse.of(sportsResponses);
    }

    /**
     * [부위별 운동 조회]
     * 1. 운동 부위 이름을 BodyPart(enum) 객체로 변환합니다.
     * 2. 부위별 운동을 조회하고 결과값을 반환합니다.
     *
     * (+) 부위별 운동의 가짓수가 많아봐야 11개 정도밖에 되지 않습니다.
     *
     */
    public MultiResponse<?> getSportsListByPart(String bodyPart, Pageable pageable) {
        log.info("bodyPart: " + bodyPart);
        Page<SimpleSportsResponse> sportsResponses = new PageImpl<>(
                sportsRepository.findByBodyPart(Sports.BodyPart.from(bodyPart), pageable)
                        .stream()
                        .map(SimpleSportsResponse::toResponse)
                        .collect(Collectors.toList()));
        log.info("sportsResponses: " + sportsResponses.toString());

        return MultiResponse.of(sportsResponses);
    }

    //-- 여기서부터 보조 기능--//
    /**
     * [sportsRequest의 목록을 sports의 목록으로 변환]
     * 1. RecordService에서 입력받은 SportsRequest의 목록을 순회하며
     *    DB에 존재하는 Sports인지 검증한 후 Sports의 목록으로 변환하여 return합니다.
     *
     * 2. sportsId의 목록으로 조회하는 방법이 더 코드와 쿼리 모두 간결하긴 합니다.
     *    두번째 주석처리된 부분이 이에 해당하는 코드입니다.
     *    다만, 이 경우 sports의 목록 전체가 null인 경우를 제외하고 일부 존재하지 않는 sports에 대한 검증이 어렵습니다.
     *
     *    프론트엔드 단에서 조회한, db에 있는 sports의 목록에 한해서만 api 요청이 들어온다는 게 보장되면
     *    후자로 바꾸는 게 깔끔할 것 같습니다.
     */
    //fixme : findAllById(List<Long> sportsIds)로 바꾸는 것 고려
    public List<Sports> getSports(List<SportsRequest> sportsRequests) {
        List<Sports> sports = new ArrayList<>();
        if (sportsRequests != null) {
            sports = sportsRequests.stream()
                    .map(sportsRequest -> findVerifiedSports(sportsRequest.getSportsId()))
                    .collect(Collectors.toList());
        }
        return sports;

        //sportsId의 목록으로 sports를 조회
//        return sportsRepository.findAllById(sportsRequests.stream()
//                .map(SportsRequest::getId).collect(Collectors.toList()));
    }

    public Sports findVerifiedSports(Long sportsId) {
        return sportsRepository.findById(sportsId).orElseThrow(SportsNotFound::new);
    }
}
