package com.backend.fitchallenge.domain.calendar.dto.request;

import com.backend.fitchallenge.domain.calendar.entity.Sports;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class SportsRequest {

    @NotBlank
    Long sportsId;

    @Positive
    Integer set;

    @Positive
    Integer count;

    @Positive
    Integer weight;
}
