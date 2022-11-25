package com.backend.fitchallenge.domain.notification.service;

import com.backend.fitchallenge.domain.challenge.entity.Challenge;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.domain.notification.dto.MultiNotificationResponse;
import com.backend.fitchallenge.domain.notification.dto.NotificationResponse;
import com.backend.fitchallenge.domain.notification.entity.Notification;
import com.backend.fitchallenge.domain.notification.exception.NotificationNotFound;
import com.backend.fitchallenge.domain.notification.repository.EmitterRepository;
import com.backend.fitchallenge.domain.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final EmitterRepository emitterRepository;
    private final NotificationRepository notificationRepository;

    /**
     * 로그인 유저 sse 연결
     * 1. memerId에 현재시간을 더해 emitterId,eventId 생성
     * 2. SseEmitter 유효시간 설정하여 생성 (시간이 지나면 자동으로 클라이언트에게 재연결 요청)
     * 3. 유효시간동안 어느 데이터도 전송되지 않을시 503에러 발생 하므로 맨처음 연결시 더미데이터 전송
     * 4. 클라이언트가 미수신한 Event 목록이 존재할 경우 전송
     * @return 생성한 SseEmitter
     */
    public SseEmitter subscribe(Long memberId, String lastEventId) {

        String emitterId = makeTimeIncludeId(memberId);

        log.info("emitterId = {}", emitterId);
        SseEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(60 * 1000 * 60L));

        //시간 초과된경우 자동으로 레포지토리에서 삭제 처리하는 콜백 등록
        emitter.onCompletion(() -> emitterRepository.deleteById(emitterId));
        emitter.onTimeout(() -> emitterRepository.deleteById(emitterId));

        //503 에러 방지 위한 더미 이벤트 전송
        String eventId = makeTimeIncludeId(memberId);
        sendNotification(emitter, eventId, emitterId, "EventStream Created. [userId=" + memberId + "]");

        // 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실 예방
        if (!lastEventId.isEmpty()) {
            sendLostData(lastEventId, memberId, emitterId, emitter);
        }
        return emitter;
    }
    /**
     * 알림 송신
     * 1. 알림생성 & 저장
     * 2. 수신자의 emitter들을 모두찾아 해당 emitter로 송신
     *
     * @param receiver 수신자
     * @param challenge 챌린지
     * @param content 알림 메시지
     */
    @Transactional
    public void send(Member receiver,  Challenge challenge,String content) {

        Notification notification = createNotification(receiver, content, challenge);
        notificationRepository.save(notification);
        String receiverId = String.valueOf(receiver.getId());
        String eventId = receiverId + "_" + System.currentTimeMillis();
        //수신자의 SseEmitter 가져오기
        Map<String, SseEmitter> emitters = emitterRepository.findAllEmitterStartWithByMemberId(receiverId);

        emitters.forEach(
                (key, emitter) -> {
                    //데이터 캐시 저장(유실된 데이터처리하기위함)
                    emitterRepository.saveEventCache(key, notification);
                    //데이터 전송
                    sendNotification(emitter, eventId, key, NotificationResponse.from(notification));
                    log.info("notification= {}", NotificationResponse.from(notification).getContent());
                });
    }

    // 알림생성
    private Notification createNotification(Member receiver, String content, Challenge challenge) {
        return Notification.builder()
                .receiver(receiver)
                .content(content)
                .url("/challenge/" + challenge.getId())
                .isRead(false)
                .build();
    }

    // 알림 전송
    private void sendNotification(SseEmitter emitter, String eventId, String emitterId, Object data) {
        try {
            emitter.send(SseEmitter.event()
                    .id(eventId)
                    .name("challenge")
                    .data(data));
        } catch (IOException e) {
            emitterRepository.deleteById(emitterId);
            log.error("SSE 연결 오류!", e);
        }
    }

    // 데이터가 유실된시점을 파악하기 위해 memberId에 현재시간을 더한다.
    private String makeTimeIncludeId(Long memberId) {
        return memberId + "_" + System.currentTimeMillis();
    }


    // 로그인후 sse연결요청시 헤더에 lastEventId가 있다면, 저장된 데이터 캐시에서 id값을 통해 유실된 데이터만 다시 전송
    private void sendLostData(String lastEventId, Long memberId, String emitterId, SseEmitter emitter) {
        Map<String, Object> eventCashes = emitterRepository.findAllEventCacheStartWithByMemberId(String.valueOf(memberId));
        eventCashes.entrySet().stream()
                .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                .forEach(entry -> sendNotification(emitter, entry.getKey(), emitterId, entry.getValue()));
    }

    //모든 알림 조회
    @Transactional
    public Object findAllById(Long memberId) {
        List<NotificationResponse> responses = notificationRepository.findAllByReceiverId(memberId).stream()
                                                             .map(NotificationResponse::from)
                                                            .collect(Collectors.toList());

        // 읽지 않은 알림수 조회
        long unreadCount = responses.stream()
                .filter(notification -> !notification.isRead())
                .count();

        return MultiNotificationResponse.of(responses, unreadCount);
    }

    // 알림 읽음 처리
    @Transactional
    public void readNotification(Long id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(NotificationNotFound::new);
        notification.read();
    }

}
