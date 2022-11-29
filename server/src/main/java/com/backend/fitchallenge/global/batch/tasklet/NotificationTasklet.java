package com.backend.fitchallenge.global.batch.tasklet;

import com.backend.fitchallenge.domain.notification.entity.Notification;
import com.backend.fitchallenge.domain.notification.repository.NotificationRepository;
import com.backend.fitchallenge.domain.notification.repository.QueryNotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@StepScope
@Slf4j
public class NotificationTasklet implements Tasklet {

    private final QueryNotificationRepository notificationRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext){
        // challenge 아이디값들끼리 비교한다.
        log.info(contribution.toString());
        log.info(chunkContext.toString());
        log.info(">>>>> Delete Notification");

        //읽음 상태인 알림 일괄삭제
        notificationRepository.deleteNotificationIsRead();
        //생성일로부터 3일이 지난 알림 일괄삭제
        notificationRepository.deleteNotificationIsNotRead();

        return RepeatStatus.FINISHED;
    }
}



