package com.backend.fitchallenge.domain.sports.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class SportsRequest {

    @NotNull(message = "운동의 id를 입력해주세요.")
    @Positive(message = "운동의 Id는 양의 정수여야 합니다.")
    Long id;

    @NotNull(message = "세트 수를 입력해주세요.")
    @Positive(message = "세트 수는 양의 정수여야 합니다.")
    Integer set;

    @NotNull(message = "횟수를 입력해주세요.")
    @Positive(message = "횟수는 양의 정수여야 합니다.")
    Integer count;

    @NotNull(message = "중량을 입력해주세요.")
    @Positive(message = "중량은 양의 정수여야 합니다.")
    Integer weight;

    public SportsRequest(Long id, Integer set, Integer count, Integer weight) {
        this.id = id;
        this.set = set;
        this.count = count;
        this.weight = weight;
    }
}
