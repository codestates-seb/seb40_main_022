package com.backend.fitchallenge.domain.workout.dto.request;

import com.backend.fitchallenge.domain.workout.entity.Sports;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class SportsDto {

    @NotBlank
    private String name;

    public Sports toEntity() {
        return Sports.of(this.name);
    }
}
