package com.backend.fitchallenge.domain.sports.service;

import com.backend.fitchallenge.domain.sports.dto.SportsRequest;
import com.backend.fitchallenge.domain.sports.dto.SportsResponse;
import com.backend.fitchallenge.domain.sports.entity.Sports;
import com.backend.fitchallenge.domain.sports.exception.SportsNotFound;
import com.backend.fitchallenge.domain.sports.repository.SportsRepository;
import com.backend.fitchallenge.global.dto.response.MultiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SportsService {

    private final SportsRepository sportsRepository;

    public MultiResponse<?> getSportsListByPart(String bodyPart, Pageable pageable) {
        log.info("bodyPart: " + bodyPart);
        Page<SportsResponse> sportsResponses = new PageImpl<>(
                sportsRepository.findByBodyPart(Sports.BodyPart.from(bodyPart), pageable)
                        .stream()
                        .map(SportsResponse::of)
                        .collect(Collectors.toList()));
        log.info("sportsResponses: " + sportsResponses.toString());

        return MultiResponse.of(sportsResponses);
    }


    public List<Sports> getSports(List<SportsRequest> sportsRequests) {
        return sportsRequests.stream()
                .map(sportsRequest -> findSports(sportsRequest.getId()))
                .collect(Collectors.toList());
    }


    public Sports findSports(Long sportsId) {
        return sportsRepository.findById(sportsId).orElseThrow(SportsNotFound::new);
    }
}
