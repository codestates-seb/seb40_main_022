package com.backend.fitchallenge.domain.notification.controller;

import com.backend.fitchallenge.domain.notification.entity.SseEmitters;
import com.backend.fitchallenge.domain.notification.service.NotificationService;
import com.backend.fitchallenge.global.annotation.AuthMember;
import com.backend.fitchallenge.global.security.userdetails.MemberDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
public class NotificationController {

    private final SseEmitters sseEmitters;
    private final NotificationService notificationService;

    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<?> connect(@AuthMember MemberDetails memberDetails,
    @RequestHeader(value = "Last-Event-ID",required = false,defaultValue = "") String lastEventId
                                     ) {

        notificationService.subscribe(memberDetails.getMemberId(), lastEventId);
        SseEmitter emitter = new SseEmitter(60 * 1000L);
        sseEmitters.add(emitter);
        try {
            emitter.send(SseEmitter.event()
                    .name("challenge") // 이벤트 이름
                    .data("challenge!")); //503에러 방지 위한 더미데이터 전달
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(emitter, HttpStatus.OK);
    }

    @PostMapping("/count")
    public ResponseEntity<?> count() {
        sseEmitters.count();
        return ResponseEntity.ok().build();
    }
}
