package com.backend.fitchallenge.domain.notification.controller;

import com.backend.fitchallenge.domain.notification.service.NotificationService;
import com.backend.fitchallenge.global.annotation.AuthMember;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@Slf4j
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    /**
     * 로그인 한 유저 sse 연결
     * @param memberDetails 로그인 세션정보
     * @param lastEventId 클라이언트가 마지막으로 수신한 데이터의 id값
     * @return
     */
    @GetMapping(value = "/connect", produces = "text/event-stream")
    public SseEmitter connect(@AuthMember MemberDetails memberDetails,
    @RequestHeader(value = "Last-Event-ID",required = false,defaultValue = "") String lastEventId) {

        SseEmitter emitter = notificationService.subscribe(memberDetails.getMemberId(), lastEventId);

        return emitter;
    }

    /**
     * 로그인 한 유저의 모든 알림 조회
     */
    @GetMapping("/notifications")
    public ResponseEntity<?> notificationList(@AuthMember MemberDetails memberDetails) {
        return new ResponseEntity<>(notificationService.findAllById(memberDetails.getMemberId()),HttpStatus.OK);
    }

    /**
     * 알림 읽음 상태 변경
     */
    @PatchMapping("/notifications/{id}")
    public ResponseEntity<?> readNotification(@PathVariable Long id) {
        notificationService.readNotification(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
