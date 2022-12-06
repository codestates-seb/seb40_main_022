package com.backend.fitchallenge.domain.notification.repository;

import com.backend.fitchallenge.domain.notification.entity.Notification;
import com.backend.fitchallenge.domain.postcomment.entity.PostComment;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.backend.fitchallenge.domain.notification.entity.QNotification.notification;
import static com.backend.fitchallenge.domain.postcomment.entity.QPostComment.postComment;
import static org.springframework.util.ObjectUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class QueryNotificationRepository {

    private final JPAQueryFactory jpaQueryFactory;


    public void deleteNotificationIsRead() {
            jpaQueryFactory
                .delete(notification)
                .where(notification.isRead.eq(true))
                .execute();
    }


    public void deleteNotificationIsNotRead() {
        jpaQueryFactory
                .delete(notification)
                .where(notification.createdAt.before(LocalDateTime.now().minusDays(3)))
                .execute();
    }


}
