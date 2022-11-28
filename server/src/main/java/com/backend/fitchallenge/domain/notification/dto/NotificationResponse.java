package com.backend.fitchallenge.domain.notification.dto;

import com.backend.fitchallenge.domain.notification.entity.Notification;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationResponse {

    private Long id;

    private String content;

    private String url; // 알림 클릭시 이동할 url

    private LocalDateTime createdAt; //알림이 생성된 날짜

    private boolean read; //알림 읽음 여부

    @Builder
    public NotificationResponse(Long id, String content, String url, LocalDateTime createdAt, boolean read) {
        this.id = id;
        this.content = content;
        this.url = url;
        this.createdAt = createdAt;
        this.read = read;
    }

    public static NotificationResponse of(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .content(notification.getContent())
                .url(notification.getUrl())
                .createdAt(notification.getCreatedAt())
                .read(notification.getIsRead())
                .build();
    }
}
