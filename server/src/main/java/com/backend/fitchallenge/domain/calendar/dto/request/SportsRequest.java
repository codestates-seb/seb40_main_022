package com.backend.fitchallenge.domain.calendar.dto.request;

import com.backend.fitchallenge.domain.calendar.entity.Sports;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class SportsRequest {

    @NotBlank
    Long sportsId;

}
