package com.backend.fitchallenge.domain.record.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TimePicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "startPicPath", nullable = false)
    private String startPicPath;

    @Column(name = "endPicPath", nullable = false)
    private String endPicPath;

    @Builder
    private TimePicture(String startPicPath, String endPicPath) {
        this.startPicPath = startPicPath;
        this.endPicPath = endPicPath;
    }

    public static TimePicture of(String startPicPath, String endPicPath) {
        return TimePicture.builder()
                .startPicPath(startPicPath)
                .endPicPath(endPicPath)
                .build();
    }

    public List<String> toPathList() {
        return List.of(this.startPicPath, this.endPicPath);
    }
}
