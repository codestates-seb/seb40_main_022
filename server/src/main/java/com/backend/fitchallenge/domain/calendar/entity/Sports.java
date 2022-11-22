package com.backend.fitchallenge.domain.calendar.entity;

import com.backend.fitchallenge.global.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Sports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SPORTS_ID", nullable = false)
    private Long id;

    @Column(nullable = false)
    String name;

    // fixme : Sports -> Record 조회가 이루어지진 않을 것.
    // 빼야 할 수도?
    @OneToMany(mappedBy = "sports", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecordSports> recordSports = new ArrayList<>();

    private Sports(BodyPart bodyPart, String name) {
        this.bodyPart = bodyPart;
        this.name = name;
    }

    public static Sports of(String bodyPartValue, String name) {
        BodyPart bodyPart = BodyPart.from(bodyPartValue);
        return new Sports(bodyPart, name);
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
         * String을 enum 객체로 변경
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
         * enum객체를 String으로 변경
         */
        @JsonValue
        public String getValue() {
            return value;
        }
    }
}
