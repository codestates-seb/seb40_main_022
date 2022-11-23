package com.backend.fitchallenge.domain.notification.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Slf4j
public class SseEmitters {

    private static final AtomicLong counter = new AtomicLong();

    //콜백이 SseEmitter를 관리하는 다른 스레드에서 실행되므로 thread safe한 자료구조 사용
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

       public  SseEmitter add(SseEmitter emitter) {
        this.emitters.add(emitter);
        log.info("new emitter added : {}", emitter);
        log.info("emitter list size: {}", emitters.size());

        //비동기 요청이 완료시 실행할 콜백 등록
        emitter.onCompletion(() -> {
            log.info("onCompletion callback");
            //타임아웃시, 브라우저에서 재연결 요청할때 새로운 Emitter 객체를 다시 생성하기 때문에 기존 Emitter 제거
            this.emitters.remove(emitter);
        });
        //타임아웃 발생 시 실행할 콜백 등록
        emitter.onTimeout(() -> {
            log.info("onTimeout callback");
            emitter.complete();
        });

        return emitter;
    }

    public void count() {
        long count = counter.incrementAndGet();
        emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event()
                        .name("count")
                        .data(count));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
