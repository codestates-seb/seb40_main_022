package com.backend.fitchallenge.domain.notification.dto;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MultiNotificationResponse {

    //로그인한 유저의 모든 알림
    private List<NotificationResponse> notificationResponses;

    //로그인 한 유저가 읽지 않은 알림 수
    private Long unreadCount;

    @Builder
    public MultiNotificationResponse(List<NotificationResponse> notificationResponses, Long unreadCount) {
        this.notificationResponses = notificationResponses;
        this.unreadCount = unreadCount;
    }

    public static MultiNotificationResponse of(List<NotificationResponse> notificationResponses, long unreadCount) {
        return MultiNotificationResponse.builder()
                .notificationResponses(notificationResponses)
                .unreadCount(unreadCount)
                .build();
    }
}
