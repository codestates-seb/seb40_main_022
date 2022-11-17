package com.backend.fitchallenge.domain.workout.entity;

import com.backend.fitchallenge.domain.workout.dto.request.SportsDto;
import com.backend.fitchallenge.global.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Sports extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SPORTS_ID", nullable = false)
    private Long id;

    @Column(nullable = false)
    String name;

    private Sports(String name) {
        this.name = name;
    }

    public static Sports of(String name) {
        return new Sports(name);
    }

    /**
     * 운동부위 설정. 추후 반영 여부 결정
     */
    @Enumerated(value = EnumType.STRING)
    BodyPart bodyPart;

    public enum BodyPart {
        LOWER_BODY("하체"),
        CHEST("가슴"),
        BACK("등"),
        SHOULDER("어깨"),
        ARM("팔"),
        ABDOMINAL("복근"),
        AEROBIC("유산소"),
        ETC("기타");

        private final String value;

        BodyPart(String value) {
            this.value = value;
        }

        /**
         * String -> enum
         */
        @JsonCreator
        public static BodyPart from(String value) {
            for (BodyPart bodyPart : BodyPart.values()) {
                if (bodyPart.getValue().equals(value))
                    return bodyPart;
            }
            return null;
        }

        /**
         * enum -> String
         */
        @JsonValue
        public String getValue() {
            return value;
        }
    }
}
