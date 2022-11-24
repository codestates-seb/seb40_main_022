package com.backend.fitchallenge.domain.notification.entity;

import com.backend.fitchallenge.domain.challenge.entity.Challenge;
import com.backend.fitchallenge.domain.member.entity.Member;
import com.backend.fitchallenge.global.audit.Auditable;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Notification extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member receiver;

    private String content;

    private  String url;

    private Boolean isRead;

    @Builder
    public Notification( Member receiver, String content, String url, Boolean isRead) {
        this.receiver = receiver;
        this.content = content;
        this.url = url;
        this.isRead = isRead;
    }

    public void read() {
        this. isRead = true;
    }
}
